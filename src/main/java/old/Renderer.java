package old;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Renderer {
  public static int DEFAULT_THREADNUM = 1;

  private final int threadnum;

  final ExecutorService renderWorker;

  public Renderer(int _threadnum){
    threadnum = _threadnum;

    renderWorker = Executors.newFixedThreadPool(threadnum);
  }

  public Renderer() {
     this(DEFAULT_THREADNUM);
  }

  public Renderer start(Runnable r){
    renderWorker.submit(r);
    return this;
  }
}
