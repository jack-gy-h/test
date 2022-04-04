<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <script type="text/javascript">

        var websocket = null;

        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/chat");
        } else {
            alert('Not support websocket');
        }
        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("error");
        };
        //连接成功建立的回调方法
        websocket.onopen = function (event) {
            setMessageInnerHTML("open");
        };
        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event);
        };
        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("close");
        };
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            websocket.close();
        };

        function setMessageInnerHTML(innerHTML) {
           alert(innerHTML);
        }

        //关闭连接
        function closeWebSocket() {
            websocket.close();
        }

        //发送消息
        function send() {
            var message = document.getElementById('text').value;
            websocket.send(message);
        }







    </script>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
