package cn.ted.design.servletChain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServletMain {

    public static void main(String[] args) {
        Request request = new Request();
        request.str="大家好:)，<script>，大家都是996！";
        Response response = new Response();
        response.str = "response ... message";

        FilterChain c = new FilterChain();
        c.add(new HTMLFilter()).
                add(new SensitiveFilter());

        FilterChain c1 = new FilterChain();
        c1.add(new SignFilter());
        c.add(c1);

        c.doFilter(request,response,c);
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
    boolean doFilter(Request req, Response resp, FilterChain chain);
}

class Request{
    String str;
}

class Response{
    String str;
}

class HTMLFilter implements Filter{
    @Override
    public boolean doFilter(Request req, Response resp, FilterChain chain) {
        String r = req.str;
        r = r.replace(":)","&&&");
        req.str = r;
        System.out.println(req.str+" HTML");

        Filter f = chain.next();
        if (f!=null) f.doFilter(req,resp,chain);
        r = resp.str;
        resp.str=r.replace("...","---");
        System.out.println(resp.str+" HTML");
        return true;
    }
}

class SensitiveFilter implements Filter{
    @Override
    public boolean doFilter(Request req, Response resp,FilterChain chain) {
        String r = req.str;
        r = r.replace("996","007");
        req.str = r;
        System.out.println(req.str +" Sense");
        Filter f = chain.next();
        if (f!=null) f.doFilter(req,resp,chain);
        r = resp.str;
        resp.str=r.replace("message"," end");
        System.out.println(resp.str +" Sense");
        return true;
    }
}

class SignFilter implements Filter{
    @Override
    public boolean doFilter(Request req, Response resp,FilterChain chain) {
        String r = req.str;
        r = r.replace("<","{");
        req.str = r;
        System.out.println(req.str +" Sign");
        Filter f = chain.next();
        if (f!=null) f.doFilter(req,resp,chain);
        r = resp.str;
        resp.str=r.replace("onse"," +++++");
        System.out.println(resp.str +" Sign");
        return true;
    }
}

class FilterChain implements Filter {
    List<Filter> filters = new LinkedList<>();
    int index = 0;

    public boolean doFilter(Request req, Response resp,FilterChain chain){
        Filter f = filters.get(0);
        if (f!=null) f.doFilter(req,resp,chain);
        return true;
    }

    public FilterChain() {

    }

    public Filter next(){
        if (index+1<filters.size())
            return filters.get(++index);
        return null;
    }

    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }
}