package evo.log;

public enum LogEvent {
  SIM_STEM(false, true, "SimStep: "),
  SIM_DONE(true, true, "SimDone. ");

  boolean active;
  boolean newLine;
  String pre;
  LogEvent(boolean active, boolean newLine, String pre){
    this.active = active;
    this.newLine = newLine;
    this.pre = pre;
  }
}
