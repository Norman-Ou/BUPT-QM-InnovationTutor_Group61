<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>History</title>
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/echarts@5.2.1/dist/echarts.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 1700px;height:600px;"></div>
<script type="text/javascript">

    var myChart = echarts.init(document.getElementById('main'));
    var option;

    option = {
        title: {
            text: 'My Smart Home'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['Humidity', 'Brightness', 'Decibel', 'Temperature', 'Distance']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [1,2,3,4,5,6,7]
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: 'Humidity',
                type: 'line',
                data: [20.6, 62.6, 75.0, 34.5, 90.0, 80.8, 21.0]
            },
            {
                name: 'Brightness',
                type: 'line',
                data: [10, 2, 1, 4, 3, 3, 7]
            },
            {
                name: 'Decibel',
                type: 'line',
                data: [50, 32, 21, 54, 90, 33, 41]
            },
            {
                name: 'Temperature',
                type: 'line',
                data: [32, 32, 31, 34, 39, 33, 32]
            },
            {
                name: 'Distance',
                type: 'line',
                data: [20, 32, 1, 34, 90, 30, 0]
            }
        ]
    };


    option && myChart.setOption(option);

</script>
</body>
</html>