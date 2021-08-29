<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link href="/css/site.css" rel="stylesheet">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>

<header>
    <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
        <div class="container">
            <a class="navbar-brand">Code Sharing Platform</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="navbar-collapse collapse d-sm-inline-flex justify-content-between">
                <ul class="navbar-nav flex-grow-1">
                    <li class="nav-item">
                        <a href="/code/latest" class="nav-link text-dark">Latest</a>
                    </li>
                    <li class="nav-item">
                        <a href="/code/new" class="nav-link text-dark">New</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="container pb-3">
    <h3>New Code</h3>

    <div style="margin-bottom: 2rem">
        <label for="code_snippet">Code: </label><br>
        <textarea placeholder="Type the code here" class="code-display" id="code_snippet" cols="50"></textarea>
        <br>

        <label for="code_snippet">Time restriction: </label><br>
        <input type="number" id="time_restriction"/>
        <br>

        <label for="code_snippet">Views restriction: </label><br>
        <input type="number" id="views_restriction"/>
        <br>
    </div>

    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</div>

<script>
    function send() {
        let object = {
            'code': document.getElementById('code_snippet').value,
            'time': Number(document.getElementById('time_restriction').value),
            'views': Number(document.getElementById('views_restriction').value)
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/code/new', true)
        xhr.responseType = 'json';
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.onloadend = function() {
            if (xhr.status === 200) {
                alert('Success! Id = ' + xhr.response.id);
            }
        };

        xhr.send(json);
    }

</script>
</body>
</html>