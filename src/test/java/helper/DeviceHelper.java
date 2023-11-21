package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

//class helper for executing bash commands
public class DeviceHelper {
    //execute bash script with full info from console
    public static String executeSh(String command) throws IOException, ExecutionException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command);// getting instance and execute script
        FutureTask<String> future = new FutureTask<>(() -> {
            return new BufferedReader(new InputStreamReader(p.getInputStream()))
                    .lines().map(Object::toString)
                    .collect(Collectors.joining("\n"));
        });
        new Thread(future).start();
        return future.get();
    }

    public static String executeBash(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final String[] message = {""};
        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while (true) {
                try {
                    if ((line = input.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                message[0] += line + "\n";
            }
            System.out.println(message[0]);
        }).start();
        try {
            p.waitFor();//wait until thread finishes
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message[0];

    }
}
