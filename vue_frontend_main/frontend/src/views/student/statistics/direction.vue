<route lang="yaml">
meta:
enabled: false
</route>


<script setup lang="ts">

import {ElMessage} from 'element-plus'
import * as Echarts from 'echarts'
import Alert from './components/alert.vue'
import 'https://webapi.amap.com/maps?v=2.0&key=3688c4eabf03099047223e5ba22ca8df&plugin=AMap.Scale,AMap.ToolBar'
import "echarts-extension-amap"
import "echarts-wordcloud"
import api from "../../../api";
import apiUser from "@/api/modules/user";


const chart1Ref = ref()
const chart2Ref = ref()
const chart3Ref = ref()
const chart4Ref = ref()
const chart5Ref = ref()
const chart6Ref = ref()
let chart1: any
let chart2: any
let chart3: any
let chart4: any
let chart5: any
let chart6: any

var search = ref({
  major: '',//mid
  direction: '',//did
  majorName: '',
  directionName: ''
})

async function initChart1() {
  chart1 = Echarts.init(chart1Ref.value)

  // 配置数据
  let bgColor = "#fff";
  let color = [
    "#0090FF",
    "#36CE9E",
    "#FFC005",
    "#FF515A",
    "#8B5CFF",
    "#00CA69"
  ];
  var echartData = []
  await api.get("/statistics/salarycapacity", {
    params: {
      directid: search.direction
    }
  }).then((res) => {
    echartData = res
  }).catch((err) => {
    ElMessage.error("请求失败" + err.code)
  })


  var yAxisData1 = await function (echartData) {
    var res = []
    //ElMessage.success("进入" + echartData.length.toString())
    for (var i = 0; i < echartData.length; i++) {
      res.push(echartData[i].num)
    }
    return res
  }

  var xAxisData = await function (echartData) {
    var res = []
    //ElMessage.success("进入" + echartData.length.toString())
    for (var i = 0; i < echartData.length; i++) {
      res.push(echartData[i].salaryrange)
    }
    return res
  }

  const hexToRgba = (hex, opacity) => {
    let rgbaColor = "";
    let reg = /^#[\da-f]{6}$/i;
    if (reg.test(hex)) {
      rgbaColor = `rgba(${parseInt("0x" + hex.slice(1, 3))},${parseInt(
        "0x" + hex.slice(3, 5)
      )},${parseInt("0x" + hex.slice(5, 7))},${opacity})`;
    }
    return rgbaColor;
  }

  const option = {
    backgroundColor: bgColor,
    color: color,
    legend: {
      right: 10,
      top: 10
    },
    tooltip: {
      trigger: "axis",
      formatter: function (params) {
        let html = '';
        params.forEach(v => {
          console.log(v)
          html += `<div style="color: #666;font-size: 14px;line-height: 24px">
                <span style="display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:${color[v.componentIndex]};"></span>
                ${v.name}k
                <span style="color:${color[v.componentIndex]};font-weight:700;font-size: 18px">${v.value}</span>
                个`;
        })
        return html
      },
      extraCssText: 'background: #fff; border-radius: 0;box-shadow: 0 0 3px rgba(0, 0, 0, 0.2);color: #333;',
      axisPointer: {
        type: 'shadow',
        shadowStyle: {
          color: '#ffffff',
          shadowColor: 'rgba(225,225,225,1)',
          shadowBlur: 5
        }
      }
    },
    grid: {
      top: 100,
      containLabel: true
    },
    xAxis: [{
      type: "",
      boundaryGap: false,
      axisLabel: {
        formatter: '{value}k',
        textStyle: {
          color: "#333"
        }
      },
      axisLine: {
        lineStyle: {
          color: "#D9D9D9"
        }
      },
      data: xAxisData(echartData)
    }],
    yAxis: [{
      type: "value",
      name: '单位：个',
      axisLabel: {
        textStyle: {
          color: "#666"
        }
      },
      nameTextStyle: {
        color: "#666",
        fontSize: 12,
        lineHeight: 40
      },
      splitLine: {
        lineStyle: {
          type: "dashed",
          color: "#E9E9E9"
        }
      },
      axisLine: {
        show: false
      },
      axisTick: {
        show: false
      }
    }],
    series: [{
      name: "2023",
      type: "line",
      smooth: true,
      // showSymbol: false,/
      symbolSize: 8,
      zlevel: 3,
      lineStyle: {
        color: color[0],
        shadowBlur: 3,
        shadowColor: hexToRgba(color[0], 0.5),
        shadowOffsetY: 8
      },
      areaStyle: {
        color: new Echarts.graphic.LinearGradient(
          0,
          0,
          0,
          1,
          [{
            offset: 0,
            color: hexToRgba(color[0], 0.3)
          },
            {
              offset: 1,
              color: hexToRgba(color[0], 0.1)
            }
          ],
          false
        ),
        shadowColor: hexToRgba(color[0], 0.1),
        shadowBlur: 10
      },
      data: yAxisData1(echartData)
    }]
  };
  // 传入数据
  chart1.setOption(option)
}

async function initChart2() {
  chart2 = Echarts.init(chart2Ref.value)
  var data = []
  await api.get("/statistics/industrycapacity", {
    params: {
      directid: search.direction
    }
  }).then((res) => {
    data = res
    //ElMessage.success("chart2请求成功"+data.length.toString())
  }).catch((err) => {
    ElMessage.error("chart2请求失败" + err.code)
  })


  // 配置数据
  const hexToRgba = (hex, opacity) => {
    let rgbaColor = "";
    let reg = /^#[\da-f]{6}$/i;
    if (reg.test(hex)) {
      rgbaColor = `rgba(${parseInt("0x" + hex.slice(1, 3))},${parseInt(
        "0x" + hex.slice(3, 5)
      )},${parseInt("0x" + hex.slice(5, 7))},${opacity})`;
    }
    return rgbaColor;
  };


// 数据整理
  var getData = await function (data) {
    var res = []
    for (var i = 0; i < data.length; i++) {
      res.push({
        name: data[i].industryName,
        value: data[i].number
      })
    }
    return res
  }
  let echartsData = getData(data)
  echartsData.sort((a, b) => b.value - a.value);
  let xData = echartsData.map(v => v.name);
  let yData = echartsData.map(v => v.value);
  let max = Math.max(...yData);
  let labelColor = ['#FD5360', '#FF962B', '#FFAA00']
  let emptyData = yData.map((v, i) => {
    let color = i > 2 ? '#1890FF' : labelColor[i];
    let item = {
      value: max,
      label: {
        formatter: '{a|' + v + '}',
        position: 'right',
        distance: 20,
        rich: {
          a: {
            color: color,
            borderColor: color,
            borderWidth: 1,
            borderType: 'dashed',
            padding: [0, 0, 2, 0],
            width: 60,
            height: 18,
            align: 'center',
            verticalAlign: 'middle',
            backgroundColor: hexToRgba(color, 0.05)
          }
        }

      }
    }
    return item
  })
  let xDataFormat = xData.map((v, i) => {
    let color = i > 2 ? '#333333' : labelColor[i];
    let item = {
      value: v,
      textStyle: {
        rich: {
          a: {
            color: color,
            width: 20,
            height: 20,
            align: 'center',
            verticalAlign: 'middle',
            backgroundColor: '#fff',
            borderRadius: 10,
            borderColor: hexToRgba(color, 0.2),
            borderWidth: 1,
            shadowColor: hexToRgba(color, 0.1),
            shadowBlur: 5
          },
          b: {
            padding: [0, 5]
          },
          value: {
            color: '#666666'
          }
        }
      }
    }
    return item
  })
  xData.reverse();
  xDataFormat.reverse();
  yData.reverse();
  emptyData.reverse();


  const option = {
    backgroundColor: '#fff',
    grid: {
      top: "5%",
      left: "1%",
      right: "15%",
      bottom: "3%",
      containLabel: true
    },
    tooltip: {
      trigger: 'item'
    },
    xAxis: [{
      type: "value",
      splitLine: {
        show: false
      },
      max: function (value) {
        return value.max
      },
      axisLine: {
        lineStyle: {
          color: '#D9D9D9'
        }
      },
      axisLabel: {
        color: '#666'
      }
    }],
    yAxis: [{
      type: "category",
      name: "",
      axisTick: {
        show: false
      },
      splitLine: {
        show: false
      },
      axisLine: {
        lineStyle: {
          color: '#D9D9D9'
        }
      },
      axisLabel: {
        formatter: function (value, index) {
          const rank = echartsData.length - index;
          return '{a|' + rank + '}{b|}{value|' + value + '}';
        }
      },
      data: xDataFormat
    }, {
      type: "category",
      name: "",
      axisTick: {
        show: false
      },
      splitLine: {
        show: false
      },
      axisLabel: {
        show: false
      },
      axisLine: {
        show: false
      },
      data: xData
    }],
    series: [{
      type: 'bar',
      barWidth: 10,
      yAxisIndex: 1,
      itemStyle: {
        barBorderRadius: [0, 6, 6, 0],
        color: 'rgba(225,225,225,0.4)'
      },
      label: {
        show: true,
        position: 'right',
        formatter: function (a) {
          console.log(a)

        }
      },
      data: emptyData
    },
      {
        type: 'bar',
        barWidth: 10,
        zlevel: 1,
        itemStyle: {
          normal: {
            barBorderRadius: [0, 6, 6, 0],
            color: new Echarts.graphic.LinearGradient(0, 0, 1, 1, [{
              offset: 0,
              color: '#3D9FFF'
            }, {
              offset: 1,
              color: '#41D7F3'
            }], false)
          }
        },
        data: yData
      }
    ]
  };
  chart2.setOption(option);
}

async function initChart3() {
  chart3 = Echarts.init(chart3Ref.value);
  // ElMessage.warning(search.direction)
  var data = []
  await api.get('/statistics/placecapacity', {
    params: {
      id: search.direction
    }
  }).then((res) => {
    data = res
    // ElMessage.success('加载成功Echart3'+data.length)
  }).catch((err) => {
    // ElMessage.error('错误代号'+err.code)
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
  // ElMessage.success(data.length)

  var convertData = await function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
      var geoCoord = geoCoordMap[data[i].placeName];
      // ElMessage.warning("#"+i+"  "+data[i].placeName+"  "+ geoCoord)
      if (geoCoord) {
        res.push({
          name: data[i].placeName,
          value: geoCoord.concat(data[i].directNumber)
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

          formatter: '{b}',
          position: 'right',
          show: false,
          emphasis: {
            show: true
          }
        },
        itemStyle: {
          color: '#ff8800'
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
  // 配置数据
  //图片 公司建筑

  var tagImg = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAiwAAAE4CAYAAAB42Kj5AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REQ3REFGOUQwNTQ1MTFFQkJBMDI5OURDMTZBMTY1RDciIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REQ3REFGOUUwNTQ1MTFFQkJBMDI5OURDMTZBMTY1RDciPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpERDdEQUY5QjA1NDUxMUVCQkEwMjk5REMxNkExNjVENyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpERDdEQUY5QzA1NDUxMUVCQkEwMjk5REMxNkExNjVENyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Piyv1GkAAFU+SURBVHja7L0HfBzH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";


  var data1 = []
  await api.get("/statistics/directtag", {
    params: {
      directid: search.direction
    }
  }).then((res) => {
    data1 = res
    //ElMessage.success("chart4请求成功" + data1.length.toString())
  }).catch((err) => {
    ElMessage.error("chart4请求失败" + err.code)
  })

  var tags = []
  data1.forEach(item => {
    tags.push(item.tagName)
  })
  // console.log(tags)
  var data = [];
  for (var i = 0; i < tags.length; ++i) {
    data.push({
      name: tags[i],
      value: (tags.length - i) * 20
    });
  }
  for (var i = 10; i < tags.length; ++i) {
    var cnt = Math.floor(Math.random() * 10);
    for (var j = 0; j < cnt; ++j) {
      data.push({
        name: tags[i],
        value: Math.random() * 10
      });
    }
  }

  var maskImage = new Image();
  maskImage.src = tagImg;
  // 传入数据

  const option = {
    backgroundColor: '#fff',
    tooltip: {
      show: false,
    },
    series: [
      {
        type: 'wordCloud',
        gridSize: 5,
        sizeRange: [12, 32],
        rotationRange: [-45, 0, 45, 90],
        maskImage: maskImage,
        textStyle: {
          color: function () {
            return 'rgb(' + [
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160)
            ].join(',') + ')';
          },

        },
        emphasis: {
          textStyle: {
            color: '#ff0000'
          },
        },
        left: 'center',
        top: 'center',
        right: null,
        bottom: null,
        width: 500,
        height: 300,
        data: data,
      },
    ],
  };
  chart4.on('click', function (params) {
    //alert((params.name));
    window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

  });
  chart4.setOption(option)
}

async function initChart6() {
  chart6 = Echarts.init(chart6Ref.value)
  // 配置数据
  //图片 公司建筑

  var company = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAiwAAAE4CAYAAAB42Kj5AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REQ3REFGOUQwNTQ1MTFFQkJBMDI5OURDMTZBMTY1RDciIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REQ3REFGOUUwNTQ1MTFFQkJBMDI5OURDMTZBMTY1RDciPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpERDdEQUY5QjA1NDUxMUVCQkEwMjk5REMxNkExNjVENyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpERDdEQUY5QzA1NDUxMUVCQkEwMjk5REMxNkExNjVENyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Piyv1GkAAFU+SURBVHja7L0HfBzH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";


  var data1 = []
  await api.get("/statistics/directcompany", {
    params: {
      directid: search.direction
    }
  }).then((res) => {
    data1 = res
    //ElMessage.success("chart6请求成功" + data1.length.toString())
  }).catch((err) => {
    ElMessage.error("chart6请求失败")
  })
  var data = []
  data1.forEach(item => {
    data.push(
      {name: item.companyName, value: item.number}
    )
  })
  var maskImage = new Image();
  maskImage.src = company;
  // 传入数据

  const option = {
    backgroundColor: '#fff',
    tooltip: {
      show: false,
    },
    series: [
      {
        type: 'wordCloud',
        gridSize: 5,
        sizeRange: [12, 32],
        rotationRange: [-45, 0, 45, 90],
        maskImage: maskImage,
        textStyle: {
          color: function () {
            return 'rgb(' + [
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160),
              Math.round(Math.random() * 160)
            ].join(',') + ')';
          },
        },
        emphasis: {
          textStyle: {
            color: '#ff0000'
          },
        },
        left: 'center',
        top: 'center',
        right: null,
        bottom: null,
        width: 500,
        height: 300,
        data: data,
      },
    ],
  };
  chart6.on('click', function (params) {
    //alert((params.name));
    window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

  });
  chart6.setOption(option)
}

async function initChart5() {
  chart5 = Echarts.init(chart5Ref.value)
  var data = []
  await api.get("/statistics/directscale", {
    params: {
      directid: search.direction
    }
  }).then((res) => {
    data = res
  }).catch((err) => {
    ElMessage.error("char5请求失败" + err.code)
  })


  const src = [
    ['score', 'product', 'amount',],
  ];

  data.forEach(item => {
    let score = item.scaledictID * 15;
    let product = '';

    switch (item.scaledictID) {
      case 1:
        product = '0-20人';
        break;
      case 2:
        product = '20-99人';
        break;
      case 3:
        product = '100-499人';
        break;
      case 4:
        product = '500-999人';
        break;
      case 5:
        product = '1000-9999人';
        break;
      case 6:
        product = '10000人以上';
        break;
      default:
        product = '';
    }

    src.push([score, product, item.number]);
    // src.push([item.scaledictID,item.scaledictID,item.number])
  });

  // console.log(data)
  // console.log(src)
  // 配置数据
  // var sorc=
  // console.log(sorc)
  const option = {
    dataset: {
      source: src
    },
    grid: {containLabel: true},
    xAxis: {name: 'amount'},
    yAxis: {type: 'category'},
    visualMap: {
      orient: 'horizontal',
      left: 'center',
      min: 10,
      max: 100,
      text: ['多', '少'],
      // Map the score column to color
      dimension: 0,
      inRange: {
        color: ['#65B581', '#FFCE34', '#FD665F']
      }
    },
    tooltip: {
      trigger: 'item'
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
        name: '公司规模分布 单位:家',
        type: 'bar',
        encode: {
          // Map the "amount" column to X axis.
          x: 'amount',
          // Map the "product" column to Y axis
          y: 'product'
        }
      }
    ]
  };
  // 传入数据
  chart5.setOption(option)
}

function open(url: string) {
  window.open(url, '_blank')
}


function clearDirection() {
  search.value.direction = ''
  directions = []
}

function LoadMajor() {
  api.get('/majors', {
    params: {
      major: ''
    }
  }).then((res) => {
    // ElMessage.success("请求发送成功")
    majors = res
  }).catch((err) => {
    ElMessage.error("请求发送失败" + err.code)
  })
}

let majorID
function setDefaultMajorDirection() {
  //TODO 改为用户的token中的mid
  apiUser.getInfo().then((res) => {
    majorID = res.data.majorid[0]
    search.major = majorID
  }).then(() => {
    console.log(search.major)
    api.get('/directions', {
      params: {
        direction: '',
        major: search.major,
      }
    }).then((res) => {
      directions=res
      search.direction = res[0].directid
      console.log(res[0].directid)
      const selectElement = document.querySelector('#mySelectDirection'); // 选择 el-select 元素
      selectElement.value = res[0].directname; // 强制将选中值设置

      initChart1()
      initChart2()
      initChart3()
      initChart4()
      initChart5()
      initChart6()
    })
    api.get('/majorname', {
      params: {
        majorid: majorID
      }
    }).then((res) => {
      console.log(res)
      const selectElement = document.querySelector('#mySelectMajor'); // 选择 el-select 元素
      selectElement.value = res; // 强制将选中值设置
    })

  })
}


onMounted(() => {
  setDefaultMajorDirection()
  LoadMajor()
  window.addEventListener('resize', () => {
    chart1.resize()
    chart2.resize()
    chart3.resize()
    chart4.resize()
    chart5.resize()
    chart6.resize()
  })
})
var majors = []
var directions = []

function LoadDirections(mid) {
  clearDirection()
  //ElMessage.success(mid.toString())
  api.get('/directions', {
    params: {
      direction: '',
      major: mid
    }
  }).then((res) => {
    // ElMessage.success("发送成功"+res.code)
    directions = res
  }).catch((err) => {
    // ElMessage.error("错误代码"+err.code)
    // ElMessage.err(search.major.toString())
  })

}

function handleSearch() {
  const selectMajor = document.getElementById('mySelectMajor'); // 选择 el-select 元素
  const selectedValueMajor = selectMajor.value; // 获取选中的值
  const selectDirecion = document.getElementById('mySelectDirection'); // 选择 el-select 元素
  const selectedValueDirection = selectDirecion.value; // 获取选中的值
  console.log(selectedValueMajor)
  console.log(selectedValueDirection)
  api.get("/majors",{
    params:{
      major:selectedValueMajor
    }
  }).then((res)=>{
    const majorID=res[0].mid
    search.major=majorID
    api.get("/directions",{
      params:{
        major:search.major,
        direction:selectedValueDirection
      }
    }).then((res)=>{
      console.log(res[0].directid)
      search.direction=res[0].directid
      ElMessage.success("查询成功")
      initChart1()
      initChart2()
      initChart3()
      initChart4()
      initChart5()
      initChart6()
    })
  })
}
</script>
<template>
  <div>
    <div>
      <PageHeader title="就业方向纵向分析" content="统计分析细化到就业方向，展示统计结果">
      </PageHeader>
      <PageMain title="选择就业方向">
        <SearchBar :show-toggle="false">
          <template #default="{ fold }">
            <ElForm :model="search" size="60px" label-width="60px" inline class="search-form">
              <ElFormItem label="专业名" class="search-form-item-name">
                <ElSelect id="mySelectMajor" v-model="search.major" clearable placeholder="请选择专业">
                  <ElOption v-for="major in majors" :label="major.majorName" :value="major.mid" :key="major.mid"
                            @click="LoadDirections(search.major)"/>
                </ElSelect>
              </ElFormItem>

              <ElFormItem v-show="fold" label="方向">
                <ElSelect id="mySelectDirection" v-model="search.direction" clearable placeholder="请选择方向">
                  <ElOption v-for="direction in directions" :label="direction.directname" :value="direction.directid"
                            :key="direction.directid"/>
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
          <PageMain title="岗位分布" style="margin: 10px 0;">
            <div ref="chart3Ref" style="width: 100%; height: 600px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
      <ElRow :gutter="20" style="margin: -10px 10px;">
        <ElCol :md="12">
          <PageMain title="薪资分布" style="margin: 10px 0;">
            <div ref="chart1Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
        <ElCol :md="12">
          <PageMain title="公司规模分布" style="margin: 10px 0;">
            <div ref="chart5Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
      <ElRow :gutter="20" style="margin: 0 10px;">
        <ElCol :md="24">
          <PageMain title="行业分布" style="margin: 10px 0;">
            <div ref="chart2Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
      <ElRow :gutter="20" style="margin: -10px 10px;">
        <ElCol :md="12">
          <PageMain title="标签词云" style="margin: 10px 0;">
            <div ref="chart4Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
        <ElCol :md="12">
          <PageMain title="公司词云" style="margin: 10px 0;">
            <div ref="chart6Ref" style="width: 100%; height: 400px;"/>
          </PageMain>
        </ElCol>
      </ElRow>
    </div>
  </div>
</template>

<style scoped lang="scss">
.search-form {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));

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
