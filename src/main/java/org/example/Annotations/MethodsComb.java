package org.example.Annotations;

import java.lang.reflect.Method;
import java.util.List;

public class MethodsComb {
    KdbTrigger kdbTrigger;
    KdbQuery kdbQuery;
    KdbCustomContraint kdbCustomContraint;
    KdbProcedure kdbProcedure;
    Method method;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public KdbProcedure getKdbProcedure() {
        return kdbProcedure;
    }

    public void setKdbProcedure(KdbProcedure kdbProcedure) {
        this.kdbProcedure = kdbProcedure;
    }

    public KdbTrigger getKdbTrigger() {
        return kdbTrigger;
    }

    public void setKdbTrigger(KdbTrigger kdbTrigger) {
        this.kdbTrigger = kdbTrigger;
    }

    public KdbQuery getKdbQuery() {
        return kdbQuery;
    }

    public void setKdbQuery(KdbQuery kdbQuery) {
        this.kdbQuery = kdbQuery;
    }

    public KdbCustomContraint getKdbCustomContraint() {
        return kdbCustomContraint;
    }

    public void setKdbCustomContraint(KdbCustomContraint kdbCustomContraint) {
        this.kdbCustomContraint = kdbCustomContraint;
    }
}
