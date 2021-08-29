<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
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
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse"
                    aria-controls="navbarSupportedContent"
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
    <div>
        <h6 class="text-opacity-75"><span id="load_date">${code.formattedUploadDate}</span></h6>
        <#if code.restrictedTime>
            <h6 class="text-opacity-75"><span id="time_restriction">This code will be available for ${code.secondsLeft} seconds</span></h6>
        </#if>

        <#if code.restrictedViews>
            <h6 class="text-opacity-75"><span id="views_restriction">${code.viewsLeft} more views allowed</span></h6>
        </#if>
    </div>
    <div class="code-display">
        <pre id="code_snippet" class="code-snippet"><code class="hljs java">${code.codeText}</code></pre>
    </div>
</div>
</body>
</html>