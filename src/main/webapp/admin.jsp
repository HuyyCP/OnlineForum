<%@ page import="DTO.SubjectStatDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Subject" %>
<%@ page import="Model.Bean.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.tiny.cloud/1/7d8d09n9dap8sawy56988twh75m0v9ld337u9abi70mpc3f3/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        .container {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
        }

        .col.border {
            background-color: #fff;
            border-radius: 5px;
            padding: 10px;
            text-align: center;
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        select.form-control {
            background-color: #fff;
            border: 1px solid #ced4da;
            border-radius: 5px;
            padding: 5px;
            width: 100%;
            font-size: 16px;
            color: #555;
        }

        #subjectStatistics {
            background-color: #fff;
            border-radius: 5px;
            height: 100px;
            width: 150px;
        }

        .col-4 .col.border {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            text-align: center;
        }

        .col-4 .col.border p {
            margin: 0;
        }

        .post-title {
            max-width: 300px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />
<jsp:include page="alert.jsp" />

    <%
        User user = (User) session.getAttribute("user");
        if(user == null || (user != null && !user.getRole().getRoleName().equals("Admin"))) {
            response.sendRedirect("/");
        }

        ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");
    %>

    <div class="container border mt-4">
        <div class="row p-3">
            <div id="numUser" class="col border mr-2"></div>
            <div id="numSubject" class="col border mr-2"></div>
            <div id="numPost" class="col border"></div>
        </div>
        <div class="row mt-3 p-3">
            <div class="col-8">
                <div class="row">
                    <div class="col-12">
                        <select class="form-control" id="subsubjectselect" name="subsubjectid">
                            <option value="All" selected>All</option>
                            <% for (Subject subject : subjects) { %>
                            <option value="<%=subject.getIdSubject()%>">
                                <a href="/subsubject/<%=subject.getIdSubject()%>">
                                    <%=subject.getSubjectName()%>
                                </a>
                            </option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <canvas id="subjectStatistics"></canvas>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="row m-3">
                    <div id="bestSubject" class="col border">
                        <p></p>
                    </div>
                </div>
                <div class="row m-3 mt-4">
                    <div id="bestPost" class="col border">
                        <p></p>
                    </div>
                </div>
                <div class="row m-3 mt-4">
                    <div id="bestUser" class="col border">
                        <p></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%
        ArrayList<SubjectStatDTO> statDTOs = (ArrayList<SubjectStatDTO>) request.getAttribute("subjectStatDTOs");
        ArrayList<Integer> numPosts = new ArrayList<>();
        ArrayList<Integer> numComments = new ArrayList<>();
        for(SubjectStatDTO dto : statDTOs) {
            numPosts.add(dto.getNumPost());
            numComments.add(dto.getNumComment());
        }
    %>

    <script>
        function drawChart(id, subjectlabels, numPosts, numComments) {
            new Chart(document.getElementById(id),
                {
                    type: 'bar',
                    data: {
                        labels: subjectlabels,
                        datasets: [
                            {
                                label: 'Bài viết',
                                data: numPosts,
                                borderWidth: 1
                            }, {
                                label: "Bình luận",
                                data: numComments,
                                borderWidth: 1
                            }
                        ]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true,
                            },
                        },
                        datasets : {
                            label: screenLeft,
                            screen : {
                                display : true,
                            }
                        },
                        plugins: {
                            legend: {
                                display: false,
                            }
                        }
                    }
                });
        }


        subjectlabels = [
            <% for(SubjectStatDTO dto : statDTOs) { %>
            '<%= dto.getSubjectName() %>',
            <% } %>
        ];

        numPosts = [
            <% for(SubjectStatDTO dto : statDTOs) { %>
            '<%= dto.getNumPost() %>',
            <% } %>
        ];
        numComments = [
            <% for(SubjectStatDTO dto : statDTOs) { %>
            '<%= dto.getNumComment() %>',
            <% } %>
        ];
        drawChart("subjectStatistics", subjectlabels, numPosts, numComments);


        document.getElementById("subsubjectselect").addEventListener("change", async (e) => {
            url = e.target.value == "All" ? "/subject/statistics" : "/subsubject/" + e.target.value;
            console.log(url);
            await fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log(JSON.stringify(data));
                subjectlabels = [];
                numPosts = [];
                numComments = [];
                data.forEach((record) => {
                    subjectlabels.push(record.subjectName);
                    numPosts.push(record.numPost);
                    numComments.push(record.numComment);
                })

                var myChart = Chart.getChart('subjectStatistics');
                if(myChart != null) myChart.destroy();
                drawChart("subjectStatistics", subjectlabels, numPosts, numComments);
            })
            .catch(error => {
                console.error('Error:', error);
            });
        })

        async function fetchUserCount() {
            try {
                const response = await fetch("/user/count");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("numUser").textContent = "Total users: " + data;
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchUserCount();

        async function fetchSubjectCount() {
            try {
                const response = await fetch("/subsubject/count");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("numSubject").textContent = "Total subjects: " + data;
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchSubjectCount();

        async function fetchPostCount() {
            try {
                const response = await fetch("/post/count");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("numPost").textContent = "Total posts: " + data;
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchPostCount();

        async function fetchBestUser() {
            try {
                const response = await fetch("/user/best");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("bestUser").innerHTML = '<p>Thành viên nổi bật: <a href="/user/' + data.idUser + '">' + data.name + '</a></p>' +
                                                                    '<p> Bài viết: ' + data.numPost + ' </p>' +
                                                                    '<p>Bình luận: ' + data.numComments + '</p>';
                // document.getElementById("bestUser").innerHTML = '<p>Thành viên nổi bật: <a href="#">' + data.name + '</a></p>' +
                //                                                 '<p> Bài viết: ' + data.numPost + ' </p>' +
                //                                                 '<p>Bình luận: ' + data.numComments + '</p>';
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchBestUser();

        async function fetchBestSubject() {
            try {
                const response = await fetch("/subsubject/best");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("bestSubject").innerHTML = '<p>Chủ đề nổi bật: <a href="/subject/' + data.idSubject + '/1">' + data.subjectName + '</a></p>' +
                                                                    '<p> Bài viết: ' + data.numPosts + ' </p>' +
                                                                    '<p>Bình luận: ' + data.numComments + '</p>';
                // document.getElementById("bestSubject").innerHTML = '<p>Chủ đề nổi bật: <a href="#">' + data.subjectName + '</a></p>' +
                //                                                     '<p> Bài viết: ' + data.numPosts + ' </p>' +
                //                                                     '<p>Bình luận: ' + data.numComments + '</p>';
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchBestSubject();

        async function fetchBestPost() {
            try {
                const response = await fetch("/post/best");
                const data = await response.json();
                console.log(JSON.stringify(data));
                document.getElementById("bestPost").innerHTML = '<p>Bài viết nổi bật: <a href="/post/' + data.idPost + '/1" class="text-truncate d-block post-title"><strong>' + data.title + '</strong></a></p>' +
                                                                '<p>Người đăng: <a href="/user/' + data.idUser + '">' + data.name + '</a></p>' +
                                                                '<p>Bình luận: ' + data.numComments + '</p>';
                // document.getElementById("bestPost").innerHTML = '<p>Bài viết nổi bật: <a href="/post/' + data.idPost + '/1">' + data.title + '</a></p>' +
                //                                                 '<p>Người đăng: <a href="#">' + data.name + '</a></p>' +
                //                                                 '<p>Bình luận: ' + data.numComments + '</p>';
            } catch (error) {
                console.error('Error:', error);
            }
        }

        fetchBestPost();
    </script>
</body>
</html>
