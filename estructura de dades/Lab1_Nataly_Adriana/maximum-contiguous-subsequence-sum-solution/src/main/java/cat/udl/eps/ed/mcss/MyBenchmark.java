package cat.udl.eps.ed.mcss;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Random;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(NANOSECONDS)
public class MyBenchmark {

    @State(Scope.Benchmark)
    public static class BenchMarkState {

        @Param({"100", "200", "300", "400", "500"})
        private int N;

        private int[] data;

        @Setup(Level.Iteration)
        public void fillArray() {
            data = new int[N];
            Random rg = new Random();
            for (int i = 0; i < N; i++)
                data[i] = 500 - rg.nextInt(1000);
        }
    }

    @Benchmark
    public void benchmarkMcss1(Blackhole blackhole, BenchMarkState state) {
        int mcss = Mcss1.mcss(state.data);
        blackhole.consume(mcss);
    }

    @Benchmark
    public void benchmarkMcss2(Blackhole blackhole, BenchMarkState state) {
        int mcss = Mcss2.mcss(state.data);
        blackhole.consume(mcss);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .measurementTime(TimeValue.seconds(1L))
                .warmupTime(TimeValue.seconds(1L))
                .build();

        new Runner(opt).run();
    }
}
