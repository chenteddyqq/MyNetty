package cn.ted.design.cor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>，大家都是996！");

        FilterChain c = new FilterChain();
        c.add(new HTMLFilter()).
                add(new SensitiveFilter());


        FilterChain c1 = new FilterChain();
        c1.add(new FaceFilter());

        c.add(c1);

        c.doFilter(msg);
        System.out.println(msg);
    }
}

class Msg {
    String name;
    String msg;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @Override
    public String toString() {
        return "Msg{"+msg + '\'' + '}';
    }
}

interface Filter{
    boolean doFilter(Msg m);
}

class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace('<','[').replace('>',']');
        m.setMsg(r);
        return true;
    }
}

class SensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replaceAll("996","995");
        m.setMsg(r);
        return true;
    }
}

class FaceFilter implements Filter{
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)","^o^");
        m.setMsg(r);
        return true;
    }
}



class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<>();

    public boolean doFilter(Msg m){
        for (Filter tmpF : filters){
            if(!tmpF.doFilter(m))
                return false;
        }
        return true;
    }

    public FilterChain() {

    }

    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }
}