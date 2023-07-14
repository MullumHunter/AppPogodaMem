package servise;

public class FirstService {
    private final SecondService secondService;

    public FirstService(SecondService secondService) {
        this.secondService = secondService;
    }

    public int doSomething(int i) {
        i = secondService.testCall(i);
        return i * 10;
    }
}
