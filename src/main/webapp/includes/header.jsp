<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--<a class="navbar-brand" href="#">Pletcher Web Design</a>-->
            <a href="../index.jsp"><img class="img-responsive" src="../images/PletcherWebDesignRevised.png" width="157" height="86"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="../index.jsp">Home <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../index.jsp">Home Page</a></li>
                        <li class="divider"></li>
                        <li><a href="../signup/signup.jsp">Sign Up</a></li>
                        <li class="divider"></li>
                        <li><a href="../forgotlogin/forgotlogin.jsp">Forgot Login</a></li>
                        <li class="divider"></li>
                        <li><a href="../review/review.jsp">Submit a Review</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Portfolio <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../portfolio/portfolio.jsp">My Portfolio</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/sigma-website.jsp">Sigma Delta Chi Website</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/vermont-tech-jobs.jsp">Vermont Tech Jobs</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/skin-and-tonic.jsp">Skin &amp; Tonic Website</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/skin-and-tonic-mobile-app.jsp">Skin &amp; Tonic Mobile</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/csharp-pos-receipt.jsp">C# Point of Sale Receipt</a></li>
                        <li class="divider"></li>
                        <li><a href="../portfolio/java-log-parse.jsp">Java Log Analytics App</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Credentials <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="../credentials.jsp">Previous Work</a></li>
                        <li class="divider"></li>
                        <li><a href="../resume.jsp">Resume</a></li>
                    </ul>
                </li>
                <li><a href="../process.jsp">Process</a></li>
                <li><a href="../craft.jsp">The Craft</a></li>
                <li><a href="../infosession/infosession.jsp">Informational Session</a></li>
                <li><a href="../contact/contact.jsp">Contact Us</a></li>
            </ul>
            <form method="post" action="../../login.action" class="navbar-form navbar-right" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" name="login.username" placeholder="Username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="login.password" placeholder="Password">
                </div>
                <button type="submit" value="Submit" class="btn btn-default">Sign In</button>
            </form>
        </div>
    </div>
</nav>