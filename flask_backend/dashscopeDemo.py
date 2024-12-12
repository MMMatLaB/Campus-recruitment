# sk-21cc505f88d9499f94ae83075bacf9e6
# For prerequisites running the following sample, visit https://help.aliyun.com/document_detail/611472.html
import random
from http import HTTPStatus
import dashscope
dashscope.api_key='sk-21cc505f88d9499f94ae83075bacf9e6'
def improve_career(message):
    return call_with_messages("""学习下面的职位简介表述格式和内容，根据所给的基本信息，生成一则职位简介：
    岗位职责：
1.负责公司产品需求分析设计，技术实现和用户体验；
2.负责所属模块的代码开发、调试与维护工作；
3.积极响应客户需求并进行开发、定制化开发与交付；
4.参与公司的架构优化、性能优化并辅助其他模块进行技术实现；
5.协助并完成其他各类技术开发任务。       
岗位要求：
1. 熟练掌握C/C++语言，熟悉springboot微服务开发框架，熟悉kubametes、spring cloud微服务运维管理平台，MysQL、MongoDB、Elasticsearch等数据模型设计及数据存取性能调优；
2. 1年以上C/C++开发经验，有产品自动化测式、性能测试、可靠性测试经验；
3.熟悉代码编程规范，熟悉代码静态检查工具原理，Devops开发和运维经验优先。
4、统招本科以上学历，计算机相关专业优先。"""
                              "基本信息:" + message+"\n 直接给出职位简介，不要输出无关信息")


def generate_report(prompt):
    return call_with_messages(prompt)


def call_with_messages(message):
    messages = [{'role': 'user', 'content': message},]

    response = dashscope.Generation.call(
        # 'qwen-72b-chat',
        'qwen-max-longcontext',
        messages = messages,
        # messages_format='list',  # set the messages to be "list" format.
        seed=random.randint(1, 10000),
        result_format='message',  # set the result to be "message" format.
    )
    if response.status_code == HTTPStatus.OK:
        print(response.output.choices[0].message.content)
        return response.output.choices[0].message.content
    else:
        print('Request id: %s, Status code: %s, error code: %s, error message: %s' % (
            response.request_id, response.status_code,
            response.code, response.message
        ))
        return response.message

if __name__ == '__main__':
    res=call_with_messages("""
根据以下数据生成2024年的毕业生就业质量报告
要求如下：
1.语法上采用markdown语法，注意使用各级标题，严格按照论文格式(标题格式：一、1，1、1，2...)，语言正式官方，具有参考价值
2.结构上分为总（一个段落，内容详实的概述）-分(若干个段落分别呈现和分析下列各数据)-总(一个段落，总结并展望未来，分析得出对学校和学生有参考价值的结论)
3.数据分析部分必须包含两部分：文字描述(对数据的文字解释和分析)+统计图表(采用markdown的table语法,填入json中的数据)的方式呈现
[
    {
        "graduatePlaceList": [
            {
                "place": "石家庄",
                "num": 91
            },
            {
                "place": "唐山",
                "num": 90
            },
            {
                "place": "秦皇岛",
                "num": 89
            }
        ] ,
        "majorGraduateGoneList": [
            {
                "majorName": "社会学",
                "graduateCountID1": 1,
                "graduateCountID2": 0,
                "graduateCountID3": 90
            },
            {
                "majorName": "数学与应用数学",
                "graduateCountID1": 0,
                "graduateCountID2": 89,
                "graduateCountID3": 0
            },
            {
                "majorName": "应用物理学",
                "graduateCountID1": 0,
                "graduateCountID2": 89,
                "graduateCountID3": 1
            }
        ],
        "majorSalaryList": [
            {
                "majorName": "社会学",
                "salary": 4.0
            },
            {
                "majorName": "数学与应用数学",
                "salary": 8.0
            },
            {
                "majorName": "应用物理学",
                "salary": 5.0
            }
        ],
        "majorRateList": [
            {
                "majorName": "社会学",
                "workrate": 0.0
            },
            {
                "majorName": "数学与应用数学",
                "workrate": 1.0
            },
            {
                "majorName": "应用物理学",
                "workrate": 0.9888888888888889
            }
        ],
        "industryGraduateList": [
            {
                "industryName": "房地产",
                "num": 91
            },
            {
                "industryName": "金融",
                "num": 90
            },
            {
                "industryName": "游戏",
                "num": 88
            },
            {
                "industryName": "银行",
                "num": 1
            }
        ]
    }
]""")
    with open('test.txt','w',encoding='utf-8') as f:
        f.write(res)

