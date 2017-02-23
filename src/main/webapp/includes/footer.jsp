<nav class="navbar navbar-inverse">
    <div class="container">
        <ul class="nav navbar-nav">
            <li><a href="../index.jsp">Home</a></li>
            <li><a href="../portfolio/portfolio.jsp">Portfolio</a></li>
            <li><a href="../credentials.jsp">Credentials</a></li>
            <li><a href="../credentials.jsp">Process</a></li>
            <li><a href="../craft.jsp">The Craft</a></li>
            <li><a href="../infosession/infosession.jsp">Informational Session</a></li>
        </ul>
        <p class="navbar-text">Copyright &copy;
            <script type="text/javascript">
                var d = new Date();
                document.write(d.getFullYear())
            </script>
            Pletcher Web Design
        </p>
    </div>

    <div class="scroll-top-wrapper ">
        <span class="scroll-top-inner">
            <i class="fa fa-2x fa-arrow-circle-up"></i>
        </span>
    </div>
</nav>

<script type="text/javascript">
    $(document).ready(function(){
        $(function(){
            $(document).on( 'scroll', function(){
                if ($(window).scrollTop() > 100) {
                    $('.scroll-top-wrapper').addClass('show');
                } else {
                    $('.scroll-top-wrapper').removeClass('show');
                }
            });
            $('.scroll-top-wrapper').on('click', scrollToTop);
        });

        function scrollToTop() {
            verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
            element = $('body');
            offset = element.offset();
            offsetTop = offset.top;
            $('html, body').animate({scrollTop: offsetTop}, 500, 'linear');
        }
    });
</script>