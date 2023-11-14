接口：
ws://localhost:8081/websocket/1	建立websocket连接
http://localhost:8081/init_json		请求类型：get	获取初始数据
http://localhost:8081/damageAnalysis	请求类型：get	对应评估界面
http://localhost:8081/ip_info_json	请求类型：post	传多机参数
{
    "node_num":2,
    "local_ip":"127.0.0.1",
    "node_ip":["127.0.0.1", "192.168.155.165"],
    "operate_time": 300,
    "area_info": []
}		此接口暂缓调试


仿真传参：
{
    "simulation":"event",	//仿真类型：time/event
    "command":"start",	//按钮：start/pause/end
    "speed":"10"		//仿真速度
}
