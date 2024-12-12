import json

import requests
from flask import Flask, jsonify, request
import hdfsdemo as hdfs
import dashscopeDemo as Qwen
app = Flask(__name__)
import flask_cors
flask_cors.CORS(app, supports_credentials=True)


@app.route('/hdfs/json',methods=['POST'])
def new_json():
    if 'file' not in request.files:
        return jsonify({'code':400,'error':'无文件'})
    file = request.files['file']
    if file.filename == '':
        return jsonify({'code':400,'error':'无文件名'})
    file_content = request.files['file']
    file_name = request.form.get('fileName')
    with open('E:/Python/CareerAnalysis/src/temp/temp.json','w',encoding='utf-8') as f:
        f.write(file_content.read().decode('utf-8'))
    hdfs.uploadJson('E:/Python/CareerAnalysis/src/temp/temp.json', file_name)
    return jsonify({'code':200,'error':''})


@app.route('/hdfs/json',methods=['GET'])
def get_json():
    file_name = request.args.get('fileName')+".json"
    # with open('test.txt','w',encoding='utf-8') as f:
    #     f.write(file_name)
    print(file_name)
    file_content = hdfs.getdata('/xiaoxin/json/'+file_name)
    # with open('test.txt','w',encoding='utf-8') as f:
    #     f.write(file_content)
    print(file_content)
    return jsonify({'code':200,'error':'','data':file_content})


@app.route('/ai/improve',methods=['POST'])
def improve_career():
    # message = request.data.get('message')
    message = request.form.get('message')
    # with open('test.txt','w',encoding='utf-8') as f:
    #     f.write(message)
    # hdfs.call_with_messages(message)
    # return jsonify({'code':200,'error':'','data':message})
    return jsonify({'code':200,'message':Qwen.improve_career(message)})


@app.route('/hdfs/report',methods=['GET'])
def get_md():
    file_name = request.args.get('url')
    try:
        file_content = hdfs.getdata('/xiaoxin/md/'+file_name+'.txt')
    except Exception as e:
        return jsonify({'code':400,'error':'文件不存在','data':''})

    return jsonify({'code':200,'error':'','data':file_content})

@app.route('/hdfs/report',methods=['POST'])
def generate_report():
    path = request.get_json().get('path')
    # print(path)
    prompt = request.get_json().get('prompt')
    content = Qwen.generate_report(json.dumps(prompt))
    try:
        hdfs.uploadMd(content, path)
    except Exception as e:
        print(e)
        return jsonify({'code':400,'error':'上传失败','data':''})

    return jsonify({'code':200,'error':''})

@app.route('/hdfs/excel',methods=['GET'])
def get_template():
    return hdfs.get_template()

if __name__ == '__main__':
    app.run(debug=True)
