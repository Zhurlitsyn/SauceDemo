//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private int attempt = 1;
    private static final int MAX_RETRY = 3;

    public Retry() {
    }

    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (this.attempt < 3) {
                ++this.attempt;
                iTestResult.setStatus(2);
                System.out.println("Retrying once again");
                return true;
            }

            iTestResult.setStatus(2);
        } else {
            iTestResult.setStatus(1);
        }

        return false;
    }
}
