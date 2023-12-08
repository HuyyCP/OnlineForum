<%@ page import="DTO.SubjectStatDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Subject" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

    <%
        ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");
    %>

    <select id="subsubjectselect" name="subsubjectid">
        <option value="All" selected>All</option>
        <% for (Subject subject : subjects) { %>
        <option value="<%=subject.getIdSubject()%>"><a href="/subsubject/<%=subject.getIdSubject()%>"><%=subject.getSubjectName()%></a></option>
        <% } %>
    </select>

    <canvas id="subjectStatistics"></canvas>

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


        document.getElementById("subsubjectselect").addEventListener("change", (e) => {
            url = e.target.value == "All" ? "/subject/statistics" : "/subsubject/" + e.target.value;
            console.log(url);

            fetch(url)
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

    </script>
</body>
</html>
