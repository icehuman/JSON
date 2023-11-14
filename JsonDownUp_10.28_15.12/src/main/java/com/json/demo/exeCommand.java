package com.json.demo;

import java.io.File;
import static com.json.demo.util.CorsFilter.logger;

import com.json.demo.dto.FrontData;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.CommandLine;


public class exeCommand {
    public int exeCommand() throws Exception {
        PumpStreamHandler pumpStreamHandler = new PumpStreamHandler();
        // 设置超时时间为5min
        ExecuteWatchdog watchdog = new ExecuteWatchdog(1000 * 60 * 5);
        DefaultExecutor executor = new DefaultExecutor();

        int code = 0;

        /**接收前端参数*/
        FrontData frontData = new FrontData();
        int node_num=frontData.getNode_num();
        String local_ip=frontData.getLocal_ip();
        String[] node_ip=frontData.getNode_ip();
        double operate_time=frontData.getOperate_time();
        Object area_info=frontData.getArea_info();
//        127.0.0.1,172.24.32.1
        try {
            CommandLine commandLine = CommandLine.parse("C:\\Program Files\\Microsoft MPI\\Bin\\mpiexec.exe -n 2");
            commandLine.addArgument("./battle1.exe ");
//            commandLine.addArgument("--help");
            commandLine.addArgument("--synch=3");
            commandLine.addArgument("--end=300");
            executor.setStreamHandler(pumpStreamHandler);
            executor.setWatchdog(watchdog);
            File f1=new File("C:\\Users\\28719\\Desktop\\ROSS-master\\bin\\models\\battle1\\Debug");
            executor.setWorkingDirectory(f1);
            code = executor.execute(commandLine);
        } catch (Exception exception) {
            if (watchdog.killedProcess()) {
                logger.error("超时了");
            }
            throw new Exception(exception.toString());
        }
        return code;

    }

}
