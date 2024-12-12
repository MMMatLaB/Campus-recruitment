<route lang="yaml">
meta:
enabled: false
</route>
<script setup lang="ts">

import {ElMessage} from 'element-plus'
import * as Echarts from 'echarts'
import 'https://webapi.amap.com/maps?v=2.0&key=3688c4eabf03099047223e5ba22ca8df&plugin=AMap.Scale,AMap.ToolBar'
import "echarts-extension-amap";
import api from "@/api";

const search = ref({
  year: '',
})

const isFold2 = ref(true)
const chart1Ref = ref()
const chart2Ref = ref()
const chart3Ref = ref()
const chart4Ref = ref()
const chart5Ref = ref()
let chart1: any
let chart2: any
let chart3: any
let chart4: any
let chart5: any
onMounted(() => {
  LoadYears()
  window.addEventListener('resize', () => {
    chart1.resize()
    chart2.resize()
    chart3.resize()
    chart4.resize()
    chart5.resize()
  })
})

let years = []

function handleSearch() {
  const selectElement = document.getElementById('mySelect'); // 选择 el-select 元素
  const selectedValue = selectElement.value; // 获取选中的值
  //console.log(selectedValue)
  search.year = selectedValue
  console.log(search.year)
  ElMessage.success("查询成功")
  initChart1()
  initChart2()
  initChart3()
  initChart4()
  initChart5()

}

async function LoadYears() {
  await api.get('/statistics/graduateyear', {
    params: {}
  }).then((res) => {
    // ElMessage.success("请求发送成功")
    years = res
    const maxYear = Math.max(...years)
    //console.log(maxYear)
    search.year = maxYear
    const selectElement = document.querySelector('#mySelect'); // 选择 el-select 元素
    selectElement.value = search.year; // 强制将选中值设置为 'option2'
    initChart1()
    initChart2()
    initChart3()
    initChart4()
    initChart5()
  })
}

async function initChart1() {
  chart1 = Echarts.init(chart1Ref.value)
  let data1 = []
  await api.get("/statistics/graduategone", {
    params: {
      year: search.year
    }
  }).then((res) => {
    data1 = res
  }).catch((err) => {
    ElMessage.error("chart1请求失败" + err.code)
  })
  // 配置数据
  let datax = []
  let y1 = []
  let y2 = []
  let y3 = []
  data1.forEach(item => {
    datax.push(item.majorName)
    y1.push(item.graduateCountID1)
    y2.push(item.graduateCountID2)
    y3.push(item.graduateCountID3)
  })

  const option = {

    backgroundColor: '#fff',
    tooltip: {
      trigger: 'axis',
      axisPointer: { // 坐标轴指示器，坐标轴触发有效
        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
      }
    },
    grid: {
      left: '2%',
      right: '4%',
      bottom: '14%',
      top: '5%',
      containLabel: true
    },
    legend: {
      data: ['1', '出国', '国内升学', '就业'],
      right: 10,
      top: 1,
      textStyle: {
        color: '#000000'
      },
      itemWidth: 12,
      itemHeight: 10,
      // itemGap: 35
    },
    xAxis: {
      type: 'category',
      data: datax,
      axisLine: {
        lineStyle: {
          color: '#000000'

        }
      },
      axisLabel: {
        // interval: 0,
        rotate: 70,
        textStyle: {
          fontFamily: 'Microsoft YaHei'
        }
      },
    },

    yAxis: {
      name: '人',
      type: 'value',

      axisLine: {
        show: false,
        lineStyle: {
          color: '#000000'
        }
      },
      splitLine: {
        show: true,
        lineStyle: {
          color: 'rgba(0,0,0,0.3)'
        }
      },
      axisLabel: {}
    },
    'dataZoom': [{
      'show': true,
      'height': 20,
      'xAxisIndex': [
        0
      ],
      bottom: '8%',
      'start': 0,
      'end': 25,
      handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
      handleSize: '110%',
      handleStyle: {
        color: '#bc8f8f',

      },
      textStyle: {
        color: '#000000'
      },
      borderColor: '#90979c'
    }, {
      'type': 'inside',
      'show': true,
      'height': 15,
      'start': 0,
      'end': 35
    }],
    series: [

      {
        name: '出国',
        type: 'bar',
        barWidth: '15%',
        itemStyle: {
          normal: {
            color: new Echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: '#65B581'
            }, {
              offset: 1,
              color: '#65B581'
            }]),
            barBorderRadius: [14, 14, 0, 0],
          }

        },
        data: y1
      },
      {
        name: '国内升学',
        type: 'bar',
        barWidth: '15%',
        itemStyle: {
          normal: {
            color: new Echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: '#FFCE34'
            }, {
              offset: 1,
              color: '#FFCE34'
            }]),
            barBorderRadius: [14, 14, 0, 0],
          }
        },
        data: y2
      },
      {
        name: '就业',
        type: 'bar',
        barWidth: '15%',
        itemStyle: {
          normal: {
            color: new Echarts.graphic.LinearGradient(0, 0, 0, 1, [{
              offset: 0,
              color: '#3366cc'
            }, {
              offset: 1,
              color: '#3366cc'
            }]),
            barBorderRadius: [14, 14, 0, 0],
          }
        },
        data: y3
      }
    ]
  };

  var app = {
    currentIndex: -1,
  };
  setInterval(function () {
    var dataLen = option.series[0].data.length;

    // 取消之前高亮的图形
    chart1.dispatchAction({
      type: 'downplay',
      seriesIndex: 0,
      dataIndex: app.currentIndex
    });
    app.currentIndex = (app.currentIndex + 1) % dataLen;
    //console.log(app.currentIndex);
    // 高亮当前图形
    chart1.dispatchAction({
      type: 'highlight',
      seriesIndex: 0,
      dataIndex: app.currentIndex,
    });
    // 显示 tooltip
    chart1.dispatchAction({
      type: 'showTip',
      seriesIndex: 0,
      dataIndex: app.currentIndex
    });
  }, 1000);
  // 传入数据
  chart1.setOption(option)
}

async function initChart2() {
  chart2 = Echarts.init(chart2Ref.value)
  let data1 = []
  await api.get("/statistics/graduatesalary", {
    params: {
      year: search.year
    }
  }).then((res) => {
    data1 = res
  }).catch((err) => {
    ElMessage.error("chart2请求失败")
  })
  // 配置数据
  let name = '千元';

  let data = [];
  data1.forEach(item => {
    data.push(
      {name: item.majorName, value: item.salary}
    )
  })

  // 按照salary的值进行排序
  data.sort(function (a, b) {
    return b.value - a.value;
  });
  let xData = data.map(v => v.name);

  let yData = data.map(v => v.value);
  const lineColor = '#224824';
  const option = {
    backgroundColor: '#fff',
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(1, 13, 19, 0.5)',
      axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
      },
      borderWidth: 0,

      textStyle: {
        color: 'rgba(255, 255, 255, 1)',
        fontSize: 12,
      },
    },
    legend: {
      show: true,
      right: '10%',
      top: '1%',
      itemWidth: 8,
      itemHeight: 8,
      textStyle: {
        color: '#000000',
      },

      // icon:'path://M0 2a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v0a2 2 0 0 1 -2 2h-14a2 2 0 0 1 -2 -2z',
    },
    grid: {
      left: '2%',
      right: '5%',
      bottom: '5%',
      top: '10%',
      containLabel: true,
    },
    xAxis: [
      {
        type: 'category',
        data: xData,
        axisLabel: {
          rotate: 70,
          fontSize: '12',
          color: '#000000',
        },
        axisTick: {
          show: false,
        },
        axisLine: {
          //坐标轴轴线相关设置。数学上的x轴
          show: true,
          lineStyle: {
            color: "#000000",
          },
        },
      },
    ],
    yAxis: [
      {
        name: name,
        nameTextStyle: {
          color: '#000000',
          fontSize: 12,
        },
        type: 'value',
        splitLine: {
          lineStyle: {
            color: lineColor,
            type: 'dashed',
          },
        }, //设置横线样式
        //     axisLine: {
        //         show: true,
        //         lineStyle: {
        //             color: lineColor,
        //         },
        //     },
        axisLabel: {
          fontSize: '12',
          color: '#000000',
        },
      },
    ],
    dataZoom: [ //给x轴设置滚动条
      {
        start: 0, //默认为0
        end: 50, //默认为100
        type: 'slider',
        show: true,
        xAxisIndex: [0],
        handleSize: 10, //滑动条的 左右2个滑动条的大小
        height: 13, //组件高度
        left: 50, //左边的距离
        right: 40, //右边的距离
        bottom: 0, //右边的距离
        handleColor: '#ddd', //h滑动图标的颜色
        handleStyle: {
          borderColor: "#cacaca",
          borderWidth: "1",
          shadowBlur: 2,
          background: "#ddd",
          shadowColor: "#ddd",
        },
        fillerColor: '#808080',
        backgroundColor: '#ddd', //两边未选中的滑动条区域的颜色
        showDataShadow: false, //是否显示数据阴影 默认auto
        showDetail: false, //即拖拽时候是否显示详细数值信息 默认true
        handleIcon: 'M-292,322.2c-3.2,0-6.4-0.6-9.3-1.9c-2.9-1.2-5.4-2.9-7.6-5.1s-3.9-4.8-5.1-7.6c-1.3-3-1.9-6.1-1.9-9.3c0-3.2,0.6-6.4,1.9-9.3c1.2-2.9,2.9-5.4,5.1-7.6s4.8-3.9,7.6-5.1c3-1.3,6.1-1.9,9.3-1.9c3.2,0,6.4,0.6,9.3,1.9c2.9,1.2,5.4,2.9,7.6,5.1s3.9,4.8,5.1,7.6c1.3,3,1.9,6.1,1.9,9.3c0,3.2-0.6,6.4-1.9,9.3c-1.2,2.9-2.9,5.4-5.1,7.6s-4.8,3.9-7.6,5.1C-285.6,321.5-288.8,322.2-292,322.2z',
        filterMode: 'filter',
      },
      //下面这个属性是里面拖到
      {
        type: 'inside',
        show: true,
        xAxisIndex: [0],
        start: 0, //默认为1
        end: 100 - 1500 / 31, //默认为100
      },
    ],
    series: [
      {
        name: '平均薪资',
        type: 'bar',
        stack: '排名',
        data: yData,
        barWidth: 8,
        itemStyle: {
          color: new Echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {
              offset: 0,
              color: '#3366cc',
            },
            {
              offset: 1,
              color: 'rgba(42, 193, 216, 0)',
            },
          ]),
        },
      },


    ],
  };
  //自己滚动图表
  var timeOut = null
  chart2.on('mouseover', stop);
  chart2.on('mouseout', goMove);
  autoMove()

  function autoMove() {
    timeOut = setInterval(() => {

      if (Number(option.dataZoom[0].end) > 100) {
        option.dataZoom[0].end = 20;
        option.dataZoom[0].start = 0;
      } else {
        option.dataZoom[0].end = option.dataZoom[0].end + 1 * (100 / option.series[0].data.length);
        option.dataZoom[0].start = option.dataZoom[0].start + 1 * (100 / option.series[0].data.length);
      }
      chart2.setOption(option);
    }, 2500);
  }

  //停止滚动
  function stop() {
    clearInterval(timeOut)
  }

  //继续滚动
  function goMove() {
    autoMove()

  }

}

async function initChart3() {
  chart3 = Echarts.init(chart3Ref.value);
  let data = []
  await api.get('/statistics/graduatelocation', {
    params: {
      year: search.year
    }
  }).then((res) => {
    data = res
    // ElMessage.success('加载成功Echart3'+data.length)
  }).catch((err) => {
    ElMessage.error('chart3加载错误' + err.code)
  })

  var geoCoordMap = {
    '石家庄': [114.48, 38.03],
    '唐山': [118.2, 39.63],
    '秦皇岛': [119.6, 39.93],
    '邯郸': [114.48, 36.62],
    '邢台': [114.48, 37.07],
    '保定': [115.47, 38.87],
    '张家口': [114.88, 40.82],
    '承德': [117.93, 40.97],
    '沧州': [116.83, 38.3],
    '廊坊': [116.7, 39.52],
    '衡水': [115.68, 37.73],
    '太原': [112.55, 37.87],
    '大同': [113.3, 40.08],
    '阳泉': [113.57, 37.85],
    '长治': [113.12, 36.2],
    '晋城': [112.83, 35.5],
    '朔州': [112.43, 39.33],
    '晋中': [112.75, 37.68],
    '运城': [111.0, 35.03],
    '忻州': [112.73, 38.42],
    '临汾': [111.52, 36.08],
    '吕梁': [111.13, 37.52],
    '沈阳': [123.43, 41.8],
    '大连': [121.62, 38.92],
    '鞍山': [122.98, 41.1],
    '抚顺': [123.98, 41.88],
    '本溪': [123.77, 41.3],
    '丹东': [124.38, 40.13],
    '锦州': [121.13, 41.1],
    '营口': [122.23, 40.67],
    '阜新': [121.67, 42.02],
    '辽阳': [123.17, 41.27],
    '盘锦': [122.07, 41.12],
    '铁岭': [123.83, 42.28],
    '朝阳': [120.45, 41.57],
    '葫芦岛': [120.83, 40.72],
    '长春': [125.32, 43.9],
    '吉林': [126.55, 43.83],
    '四平': [124.35, 43.17],
    '辽源': [125.13, 42.88],
    '通化': [125.93, 41.73],
    '白山': [126.42, 41.93],
    '松原': [124.82, 45.13],
    '白城': [122.83, 45.62],
    '延边朝鲜族自治州': [129.5, 42.88],
    '哈尔滨': [126.53, 45.8],
    '齐齐哈尔': [123.95, 47.33],
    '鸡西': [130.97, 45.3],
    '鹤岗': [130.27, 47.33],
    '双鸭山': [131.15, 46.63],
    '大庆': [125.03, 46.58],
    '伊春': [128.9, 47.73],
    '佳木斯': [130.37, 46.82],
    '七台河': [130.95, 45.78],
    '牡丹江': [129.6, 44.58],
    '黑河': [127.48, 50.25],
    '绥化': [126.98, 46.63],
    '大兴安岭地区': [124.12, 50.42],
    '南京': [118.78, 32.07],
    '无锡': [120.3, 31.57],
    '徐州': [117.18, 34.27],
    '常州': [119.95, 31.78],
    '苏州': [120.58, 31.3],
    '南通': [120.88, 31.98],
    '连云港': [119.22, 34.6],
    '淮安': [119.02, 33.62],
    '盐城': [120.15, 33.35],
    '扬州': [119.4, 32.4],
    '镇江': [119.45, 32.2],
    '泰州': [119.92, 32.45],
    '宿迁': [118.28, 33.97],
    '杭州': [120.15, 30.28],
    '宁波': [121.55, 29.88],
    '温州': [120.7, 28.0],
    '嘉兴': [120.75, 30.75],
    '湖州': [120.08, 30.9],
    '绍兴': [120.57, 30.0],
    '金华': [119.65, 29.08],
    '衢州': [118.87, 28.93],
    '舟山': [122.2, 30.0],
    '台州': [121.43, 28.68],
    '丽水': [119.92, 28.45],
    '合肥': [117.25, 31.83],
    '芜湖': [118.38, 31.33],
    '蚌埠': [117.38, 32.92],
    '淮南': [117.0, 32.63],
    '马鞍山': [118.5, 31.7],
    '淮北': [116.8, 33.95],
    '铜陵': [117.82, 30.93],
    '安庆': [117.05, 30.53],
    '黄山': [118.33, 29.72],
    '滁州': [118.32, 32.3],
    '阜阳': [115.82, 32.9],
    '宿州': [116.98, 33.63],
    '六安': [116.5, 31.77],
    '亳州': [115.78, 33.85],
    '池州': [117.48, 30.67],
    '宣城': [118.75, 30.95],
    '福州': [119.3, 26.08],
    '厦门': [118.08, 24.48],
    '莆田': [119.0, 25.43],
    '三明': [117.62, 26.27],
    '泉州': [118.67, 24.88],
    '漳州': [117.65, 24.52],
    '南平': [118.17, 26.65],
    '龙岩': [117.03, 25.1],
    '宁德': [119.52, 26.67],
    '南昌': [115.85, 28.68],
    '景德镇': [117.17, 29.27],
    '萍乡': [113.85, 27.63],
    '九江': [115.97, 29.72],
    '新余': [114.92, 27.82],
    '鹰潭': [117.07, 28.27],
    '赣州': [114.93, 25.83],
    '吉安': [114.98, 27.12],
    '宜春': [114.38, 27.8],
    '抚州': [116.35, 28.0],
    '上饶': [117.97, 28.45],
    '济南': [117.0, 36.65],
    '青岛': [120.38, 36.07],
    '淄博': [118.05, 36.82],
    '枣庄': [117.32, 34.82],
    '东营': [118.67, 37.43],
    '烟台': [121.43, 37.45],
    '潍坊': [119.15, 36.7],
    '济宁': [116.58, 35.42],
    '泰安': [117.08, 36.2],
    '威海': [122.12, 37.52],
    '日照': [119.52, 35.42],
    '莱芜': [117.67, 36.22],
    '临沂': [118.35, 35.05],
    '德州': [116.3, 37.45],
    '聊城': [115.98, 36.45],
    '滨州': [118.03, 37.37],
    '菏泽': [115.43, 35.25],
    '郑州': [113.62, 34.75],
    '开封': [114.3, 34.8],
    '洛阳': [112.45, 34.62],
    '平顶山': [113.18, 33.77],
    '安阳': [114.38, 36.1],
    '鹤壁': [114.28, 35.75],
    '新乡': [113.9, 35.3],
    '焦作': [113.25, 35.22],
    '濮阳': [115.03, 35.77],
    '许昌': [113.85, 34.03],
    '漯河': [114.02, 33.58],
    '三门峡': [111.2, 34.78],
    '南阳': [112.52, 33.0],
    '商丘': [115.65, 34.45],
    '信阳': [114.07, 32.13],
    '周口': [114.65, 33.62],
    '驻马店': [114.02, 32.98],
    '济源': [112.58, 35.07],
    '武汉': [114.3, 30.6],
    '黄石': [115.03, 30.2],
    '十堰': [110.78, 32.65],
    '宜昌': [111.28, 30.7],
    '襄阳': [112.15, 32.02],
    '鄂州': [114.88, 30.4],
    '荆门': [112.2, 31.03],
    '孝感': [113.92, 30.93],
    '荆州': [112.23, 30.33],
    '黄冈': [114.87, 30.45],
    '咸宁': [114.32, 29.85],
    '随州': [113.37, 31.72],
    '恩施土家族苗族自治州': [109.47, 30.3],
    '仙桃': [113.45, 30.37],
    '潜江': [112.88, 30.42],
    '天门': [113.17, 30.67],
    '神农架林区': [110.67, 31.75],
    '长沙': [112.93, 28.23],
    '株洲': [113.13, 27.83],
    '湘潭': [112.93, 27.83],
    '衡阳': [112.57, 26.9],
    '邵阳': [111.47, 27.25],
    '岳阳': [113.12, 29.37],
    '常德': [111.68, 29.05],
    '张家界': [110.47, 29.13],
    '益阳': [112.32, 28.6],
    '郴州': [113.02, 25.78],
    '永州': [111.62, 26.43],
    '怀化': [109.98, 27.55],
    '娄底': [111.97, 27.73],
    '湘西土家族苗族自治州': [109.73, 28.32],
    '广州': [113.27, 23.13],
    '韶关': [113.6, 24.82],
    '深圳': [114.07, 22.62],
    '珠海': [113.57, 22.27],
    '汕头': [116.68, 23.35],
    '佛山': [113.12, 23.02],
    '江门': [113.08, 22.58],
    '湛江': [110.35, 21.27],
    '茂名': [110.92, 21.67],
    '肇庆': [112.47, 23.05],
    '惠州': [114.42, 23.12],
    '梅州': [116.12, 24.28],
    '汕尾': [115.37, 22.78],
    '河源': [114.7, 23.73],
    '阳江': [111.98, 21.87],
    '清远': [113.03, 23.7],
    '东莞': [113.75, 23.05],
    '中山': [113.38, 22.52],
    '潮州': [116.62, 23.67],
    '揭阳': [116.37, 23.55],
    '云浮': [112.03, 22.92],
    '海口': [110.32, 20.03],
    '三亚': [109.5, 18.25],
    '三沙': [112.33, 16.83],
    '儋州': [109.57, 19.52],
    '五指山': [109.52, 18.78],
    '琼海': [110.47, 19.25],
    '文昌': [110.8, 19.55],
    '万宁': [110.4, 18.8],
    '东方': [108.63, 19.1],
    '成都': [104.07, 30.67],
    '自贡': [104.78, 29.35],
    '攀枝花': [101.72, 26.58],
    '泸州': [105.43, 28.87],
    '德阳': [104.38, 31.13],
    '绵阳': [104.73, 31.47],
    '广元': [105.83, 32.43],
    '遂宁': [105.57, 30.52],
    '内江': [105.05, 29.58],
    '乐山': [103.77, 29.57],
    '南充': [106.08, 30.78],
    '眉山': [103.83, 30.05],
    '宜宾': [104.62, 28.77],
    '广安': [106.63, 30.47],
    '达州': [107.5, 31.22],
    '雅安': [103.0, 29.98],
    '巴中': [106.77, 31.85],
    '资阳': [104.65, 30.12],
    '阿坝藏族羌族自治州': [102.22, 31.9],
    '甘孜藏族自治州': [101.97, 30.05],
    '凉山彝族自治州': [102.27, 27.9],
    '贵阳': [106.63, 26.65],
    '六盘水': [104.83, 26.6],
    '遵义': [106.92, 27.73],
    '安顺': [105.95, 26.25],
    '毕节': [105.28, 27.3],
    '铜仁': [109.18, 27.72],
    '黔西南布依族苗族自治州': [104.9, 25.08],
    '黔东南苗族侗族自治州': [107.97, 26.58],
    '黔南布依族苗族自治州': [107.52, 26.27],
    '昆明': [102.72, 25.05],
    '曲靖': [103.8, 25.5],
    '玉溪': [102.55, 24.35],
    '保山': [99.17, 25.12],
    '昭通': [103.72, 27.33],
    '丽江': [100.23, 26.88],
    '普洱': [100.97, 22.78],
    '临沧': [100.08, 23.88],
    '楚雄彝族自治州': [101.55, 25.03],
    '红河哈尼族彝族自治州': [103.4, 23.37],
    '文山壮族苗族自治州': [104.25, 23.37],
    '西双版纳傣族自治州': [100.8, 22.02],
    '大理白族自治州': [100.23, 25.6],
    '德宏傣族景颇族自治州': [98.58, 24.43],
    '怒江傈僳族自治州': [98.85, 25.85],
    '迪庆藏族自治州': [99.7, 27.83],
    '西安': [108.93, 34.27],
    '铜川': [108.93, 34.9],
    '宝鸡': [107.13, 34.37],
    '咸阳': [108.7, 34.33],
    '渭南': [109.5, 34.5],
    '延安': [109.48, 36.6],
    '汉中': [107.02, 33.07],
    '榆林': [109.73, 38.28],
    '安康': [109.02, 32.68],
    '商洛': [109.93, 33.87],
    '兰州': [103.82, 36.07],
    '嘉峪关': [98.27, 39.8],
    '金昌': [102.18, 38.5],
    '白银': [104.18, 36.55],
    '天水': [105.72, 34.58],
    '武威': [102.63, 37.93],
    '张掖': [100.45, 38.93],
    '平凉': [106.67, 35.55],
    '酒泉': [98.52, 39.75],
    '庆阳': [107.63, 35.73],
    '定西': [104.62, 35.58],
    '陇南': [104.92, 33.4],
    '临夏回族自治州': [103.22, 35.6],
    '甘南藏族自治州': [102.92, 34.98],
    '西宁': [101.78, 36.62],
    '海东': [102.12, 36.5],
    '海北藏族自治州': [100.9, 36.97],
    '黄南藏族自治州': [102.02, 35.52],
    '海南藏族自治州': [100.62, 36.28],
    '果洛藏族自治州': [100.23, 34.48],
    '玉树藏族自治州': [97.02, 33.0],
    '海西蒙古族藏族自治州': [97.37, 37.37],
    '台北': [121.5, 25.03],
    '高雄': [120.28, 22.62],
    '基隆': [121.73, 25.13],
    '台中': [120.67, 24.15],
    '台南': [120.2, 23.0],
    '新竹': [120.95, 24.82],
    '嘉义': [120.43, 23.48],
    '新北': [121.47, 25.02],
    '宜兰': [121.75, 24.75],
    '桃园': [121.3, 24.97],
    '苗栗': [120.8, 24.53],
    '彰化': [120.53, 24.08],
    '南投': [120.67, 23.92],
    '云林': [120.53, 23.72],
    '屏东': [120.48, 22.67],
    '台东': [121.15, 22.75],
    '花莲': [121.6, 23.98],
    '澎湖': [119.58, 23.58],
    '呼和浩特': [111.65, 40.82],
    '包头': [109.83, 40.65],
    '乌海': [106.82, 39.67],
    '赤峰': [118.92, 42.27],
    '通辽': [122.27, 43.62],
    '鄂尔多斯': [109.8, 39.62],
    '呼伦贝尔': [119.77, 49.22],
    '巴彦淖尔': [107.42, 40.75],
    '乌兰察布': [113.12, 40.98],
    '兴安盟': [122.05, 46.08],
    '锡林郭勒盟': [116.07, 43.95],
    '阿拉善盟': [105.67, 38.83],
    '南宁': [108.37, 22.82],
    '柳州': [109.42, 24.33],
    '桂林': [110.28, 25.28],
    '梧州': [111.27, 23.48],
    '北海': [109.12, 21.48],
    '防城港': [108.35, 21.7],
    '钦州': [108.62, 21.95],
    '贵港': [109.6, 23.1],
    '玉林': [110.17, 22.63],
    '百色': [106.62, 23.9],
    '贺州': [111.55, 24.42],
    '河池': [108.07, 24.7],
    '来宾': [109.23, 23.73],
    '崇左': [107.37, 22.4],
    '拉萨': [91.13, 29.65],
    '日喀则': [88.88, 29.27],
    '昌都': [97.18, 31.13],
    '林芝': [94.37, 29.68],
    '山南': [91.77, 29.23],
    '那曲地区': [92.07, 31.48],
    '阿里地区': [80.1, 32.5],
    '银川': [106.28, 38.47],
    '石嘴山': [106.38, 39.02],
    '吴忠': [106.2, 37.98],
    '固原': [106.28, 36.0],
    '中卫': [105.18, 37.52],
    '乌鲁木齐': [87.62, 43.82],
    '克拉玛依': [84.87, 45.6],
    '吐鲁番': [89.17, 42.95],
    '哈密': [93.52, 42.83],
    '昌吉回族自治州': [87.3, 44.02],
    '博尔塔拉蒙古自治州': [82.07, 44.9],
    '巴音郭楞蒙古自治州': [86.15, 41.77],
    '阿克苏地区': [80.27, 41.17],
    '克孜勒苏柯尔克孜自治州': [76.17, 39.72],
    '喀什地区': [75.98, 39.47],
    '和田地区': [79.92, 37.12],
    '伊犁哈萨克自治州': [81.32, 43.92],
    '塔城地区': [82.98, 46.75],
    '阿勒泰地区': [88.13, 47.85],
    '自治区直辖县级行政区划': [86.03, 44.3],
    '北京': [116.4, 39.9],
    '天津': [117.2, 39.12],
    '上海': [121.47, 31.23],
    '重庆': [106.55, 29.57],
    '香港': [114.17, 22.28],
    '澳门': [113.55, 22.17]
  };

  var convertData = await function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
      var geoCoord = geoCoordMap[data[i].place];
      // ElMessage.warning("#"+i+"  "+data[i].placeName+"  "+ geoCoord)
      if (geoCoord) {
        res.push({
          name: data[i].place,
          value: geoCoord.concat(data[i].num)
        });
      }
    }
    return res;
  };
  // ECharts Option配置
  var option = {
    // 加载 amap 组件
    amap: {
      // 高德地图中心经纬度
      center: [108.39, 39.9],
      // 高德地图缩放
      zoom: 4,
      // 启用resize
      resizeEnable: true,
      // 移动过程中实时渲染 默认为true 如数据量较大 建议置为false
      renderOnMoving: true,
      // 自定义地图样式主题
      mapStyle: 'amap://styles/normal'
      // 说明：如果想要添加卫星、路网等图层
      // 暂时先不要使用layers配置，因为存在Bug
      // 建议使用amap.add的方式，使用方式参见最下方代码

      // 其他高德地图支持的初始配置项都可以在这里配置
    },
    tooltip: {
      trigger: 'item'
    },
    animation: true,
    series: [
      {
        name: '岗位数量',
        type: 'scatter',
        // 使用高德地图坐标系
        coordinateSystem: 'amap',
        data: convertData(data),
        symbolSize: function (val) {
          return val[2] / 3;
        },
        encode: {
          // 编码使用数组中第三个元素作为value维度
          value: 2
        },
        label: {
          normal: {
            formatter: '{b}',
            position: 'right',
            show: false
          },
          emphasis: {
            show: true
          }
        },
        itemStyle: {
          normal: {
            color: '#ff8800'
          }
        }
      },
      {
        name: 'Top 5',
        type: 'effectScatter',
        coordinateSystem: 'amap',
        data: convertData(data.sort(function (a, b) {
          return b.value - a.value;
        }).slice(0, 6)),
        symbolSize: function (val) {
          return val[2] / 3;
        },
        encode: {
          value: 2
        },
        showEffectOn: 'render',
        rippleEffect: {
          brushType: 'stroke'
        },
        hoverAnimation: true,
        label: {
          normal: {
            formatter: '{b}',
            position: 'right',
            show: true
          }
        },
        itemStyle: {
          normal: {
            color: '#ff0000',
            shadowBlur: 10,
            shadowColor: '#333'
          }
        },
        zlevel: 1
      }
    ]
  };
  // 初始化ECharts

  chart3.setOption(option);
  // 从ECharts实例中取到高德地图组件实例
  var amap = chart3.getModel().getComponent('amap').getAMap();
  // 下边就可以按照高德官方API随意调用了
  // 比如添加一些控件
  amap.addControl(new AMap.Scale());
  amap.addControl(new AMap.ToolBar());
  // 添加一些图层 卫星图层/交通路网等等
  // var satelliteLayer = new AMap.TileLayer.Satellite();
  // var roadNetLayer = new AMap.TileLayer.RoadNet();
  // amap.add([satelliteLayer, roadNetLayer]);
}

async function initChart4() {
  chart4 = Echarts.init(chart4Ref.value)
  let data1 = []
  await api.get("/statistics/graduateworkrate", {
    params: {
      year: search.year
    }
  }).then((res) => {
    data1 = res
  }).catch((err) => {
    ElMessage.error("chart2请求失败")
  })
  // 配置数据
  let name = '%';
  let data = [];
  data1.forEach(item => {
    data.push(
      {name: item.majorName, value: item.workrate * 100}
    )
  })
  console.log(data)
  // var datax = ["国际经济与贸易学","法学","社会学","数学与应用数学","应用物理学","工程力学","机械工程","材料科学与工程","能源与动力工程","电气工程及其自动化","电子科学与技术","信息工程","自动化","计算机科学与技术","工商管理","金融学","软件工程","会计学","微电子科学与工程","车辆工程","过程装备与控制工程","核工程与核技术","生物医学工程","临床医学","工业工程","信息与计算科学","思想政治教育","应用化学","统计学","材料物理","新能源科学与工程","飞行器设计与工程","口腔医学","预防医学","法医学","劳动与社会保障","财政学","贸易经济","电子商务"];
  // var datay1 = [83, 92, 84, 92, 93, 81, 85, 97, 98, 91, 88, 82, 95, 88, 89, 94, 95, 88, 86, 89, 97, 87, 94, 83, 88, 89, 82, 96, 81, 87, 83, 82, 94, 96, 92, 96, 81, 87, 87, 93];
  // // 将datax和datay1关联为一个对象数组
  // var data = [];
  // for (var i = 0; i < datax.length; i++) {
  //   data.push({
  //     name: datax[i],
  //     value: datay1[i]
  //   });
  // }

  // 按照datay1的值进行排序
  data.sort(function (a, b) {
    return b.value - a.value;
  });
  let xData = data.map(v => v.name);

  let yData = data.map(v => v.value);
  const lineColor = '#224824';
  const option = {
    backgroundColor: '#fff',
    tooltip: {
      trigger: 'axis',
      backgroundColor: 'rgba(1, 13, 19, 0.5)',
      axisPointer: {
        // 坐标轴指示器，坐标轴触发有效
        type: 'shadow', // 默认为直线，可选为：'line' | 'shadow'
      },
      borderWidth: 0,

      textStyle: {
        color: 'rgba(255, 255, 255, 1)',
        fontSize: 12,
      },
    },
    legend: {
      show: true,
      right: '10%',
      top: '1%',
      itemWidth: 8,
      itemHeight: 8,
      textStyle: {
        color: '#000000',
      },

      // icon:'path://M0 2a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v0a2 2 0 0 1 -2 2h-14a2 2 0 0 1 -2 -2z',
    },
    grid: {
      left: '2%',
      right: '5%',
      bottom: '5%',
      top: '10%',
      containLabel: true,
    },
    xAxis: [
      {
        type: 'category',
        data: xData,
        axisLabel: {
          rotate: 70,
          fontSize: '12',
          color: '#000000',
        },
        axisTick: {
          show: false,
        },
        axisLine: {
          //坐标轴轴线相关设置。数学上的x轴
          show: true,
          lineStyle: {
            color: "#000000",
          },
        },
      },
    ],
    yAxis: [
      {
        name: name,
        nameTextStyle: {
          color: '#000000',
          fontSize: 12,
        },
        type: 'value',
        splitLine: {
          lineStyle: {
            color: lineColor,
            type: 'dashed',
          },
        }, //设置横线样式
        //     axisLine: {
        //         show: true,
        //         lineStyle: {
        //             color: lineColor,
        //         },
        //     },
        axisLabel: {
          fontSize: '12',
          color: '#000000',
        },
      },
    ],
    dataZoom: [ //给x轴设置滚动条
      {
        start: 0, //默认为0
        end: 50, //默认为100
        type: 'slider',
        show: true,
        xAxisIndex: [0],
        handleSize: 10, //滑动条的 左右2个滑动条的大小
        height: 13, //组件高度
        left: 50, //左边的距离
        right: 40, //右边的距离
        bottom: 0, //右边的距离
        handleColor: '#ddd', //h滑动图标的颜色
        handleStyle: {
          borderColor: "#cacaca",
          borderWidth: "1",
          shadowBlur: 2,
          background: "#ddd",
          shadowColor: "#ddd",
        },
        fillerColor: '#808080',
        backgroundColor: '#ddd', //两边未选中的滑动条区域的颜色
        showDataShadow: false, //是否显示数据阴影 默认auto
        showDetail: false, //即拖拽时候是否显示详细数值信息 默认true
        handleIcon: 'M-292,322.2c-3.2,0-6.4-0.6-9.3-1.9c-2.9-1.2-5.4-2.9-7.6-5.1s-3.9-4.8-5.1-7.6c-1.3-3-1.9-6.1-1.9-9.3c0-3.2,0.6-6.4,1.9-9.3c1.2-2.9,2.9-5.4,5.1-7.6s4.8-3.9,7.6-5.1c3-1.3,6.1-1.9,9.3-1.9c3.2,0,6.4,0.6,9.3,1.9c2.9,1.2,5.4,2.9,7.6,5.1s3.9,4.8,5.1,7.6c1.3,3,1.9,6.1,1.9,9.3c0,3.2-0.6,6.4-1.9,9.3c-1.2,2.9-2.9,5.4-5.1,7.6s-4.8,3.9-7.6,5.1C-285.6,321.5-288.8,322.2-292,322.2z',
        filterMode: 'filter',
      },
      //下面这个属性是里面拖到
      {
        type: 'inside',
        show: true,
        xAxisIndex: [0],
        start: 0, //默认为1
        end: 100 - 1500 / 31, //默认为100
      },
    ],
    series: [
      {
        name: '就业率',
        type: 'bar',
        stack: '排名',
        data: yData,
        barWidth: 8,
        itemStyle: {
          color: new Echarts.graphic.LinearGradient(0, 0, 0, 1, [
            {
              offset: 0,
              color: '#FD665F',
            },
            {
              offset: 1,
              color: 'rgba(42, 193, 216, 0)',
            },
          ]),
        },
      },


    ],
  };
  //自己滚动图表
  var timeOut = null
  chart4.on('mouseover', stop);
  chart4.on('mouseout', goMove);
  autoMove()

  function autoMove() {
    timeOut = setInterval(() => {

      if (Number(option.dataZoom[0].end) > 100) {
        option.dataZoom[0].end = 20;
        option.dataZoom[0].start = 0;
      } else {
        option.dataZoom[0].end = option.dataZoom[0].end + 1 * (100 / option.series[0].data.length);
        option.dataZoom[0].start = option.dataZoom[0].start + 1 * (100 / option.series[0].data.length);
      }
      chart4.setOption(option);
    }, 2500);
  }

  //停止滚动
  function stop() {
    clearInterval(timeOut)
  }

  //继续滚动
  function goMove() {
    autoMove()

  }

}

async function initChart5() {
  chart5 = Echarts.init(chart5Ref.value)
  let data1 = []
  await api.get("/statistics/graduateindustry", {
    params: {
      year: search.year
    }
  }).then((res) => {
    data1 = res
  }).catch((err) => {
    ElMessage.error("chart5请求失败")
  })
  let data = []
  data1.forEach(item => {
    data.push(
      {value: item.num, name: item.industryName}
    )
  })
  // 配置数据
  const option = {
    legend: {
      orient: 'vertical',
      right: '10%',
    },
    toolbox: {
      show: true,
      feature: {
        mark: {show: true},
        dataView: {show: true, readOnly: false},
        restore: {show: true},
        saveAsImage: {show: true}
      }
    },
    series: [
      {
        name: '行业分布',
        type: 'pie',
        radius: [50, 200],
        center: ['30%', '50%'],
        roseType: 'area',
        itemStyle: {
          borderRadius: 8
        },
        data: data
      }
    ]
  };
  // 传入数据
  chart5.setOption(option)
}

function open(url: string) {
  window.open(url, '_blank')
}
</script>

<template>
  <div>
    <div>
      <PageHeader title="毕业生去向数据分析" content="统计分析所有专业毕业生信息，展示统计结果。">
      </PageHeader>
      <PageMain title="选择年份">
        <SearchBar :fold="isFold2" :show-toggle="false">
          <template #default="{ fold }">
            <ElForm :model="search" size="default" label-width="60px" inline class="search-form">
              <ElFormItem label="年份" class="search-form-item-name">
                <ElSelect id="mySelect" v-model="search.year" placeholder="请选择年份" clearable>
                  <ElOption v-for="year in years" :label="year" :value="year" :key="year"/>
                </ElSelect>
              </ElFormItem>
              <ElFormItem/>
              <ElFormItem>
                <ElButton type="primary" @click="handleSearch">
                  <template #icon>
                    <SvgIcon name="ep:search"/>
                  </template>
                  选择
                </ElButton>
              </ElFormItem>
            </ElForm>
          </template>
        </SearchBar>
      </PageMain>
    </div>

    <div>
      <ElRow :gutter="20" style="margin: -10px 10px;">
        <ElCol :md="24">
          <PageMain title="毕业生地域分布" style="margin: 10px 0;">
            <div ref="chart3Ref" style="width: 100%; height: 600px;"/>
          </PageMain>
        </ElCol>

      </ElRow>
      <ElRow :gutter="20" style="margin: 0 10px;">
        <ElCol :md="24">
          <PageMain title="毕业生去向" style="margin: 10px 0;">
            <div ref="chart1Ref" style="width: 100%; height: 600px;"/>
          </PageMain>
        </ElCol>
        <ElCol :md="12">
          <PageMain title="各专业毕业生平均薪资" style="margin: 10px 0;">
            <div ref="chart2Ref" style="width: 100%; height: 300px;"/>
          </PageMain>
        </ElCol>
        <ElCol :md="12">
          <PageMain title="各专业毕业生落实率" style="margin: 10px 0;">
            <div ref="chart4Ref" style="width: 100%; height: 300px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
      <ElRow :gutter="20" style="margin: 0 10px;">

        <ElCol :md="24">
          <PageMain title="行业分布" style="margin: 10px 0;">
            <div ref="chart5Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
    </div>
  </div>
</template>

<style scoped lang="scss">
.search-form {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));

  :deep(.el-form-item) {
    grid-column: auto / span 1;

    &:last-child {
      grid-column-end: -1;

      .el-form-item__content {
        justify-content: flex-end;
      }
    }

    &.search-form-item-name {
      grid-column: auto / span 2;
    }
  }
}

.action-box {
  :deep(.el-form-item__content) {
    justify-content: flex-end;
  }
}
</style>
