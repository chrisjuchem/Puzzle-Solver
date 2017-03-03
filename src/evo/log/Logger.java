package evo.log;

public class Logger {

  public static void log(String msg, LogEvent logEvent) {
    if (logEvent.active) {
      System.out.print(logEvent.pre);
      System.out.print(msg);
      if (logEvent.newLine) {
        System.out.println();
      }
    }
  }
}
