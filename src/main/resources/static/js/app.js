var MyScroll = "";
(function (window, document, $, undefined) {
  "use strict";
  var isMobile =
    /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Nokia|Opera Mini/i.test(
      navigator.userAgent
    )
      ? !0
      : !1;
  var Scrollbar = window.Scrollbar;
  var Init = {
    i: function (e) {
      Init.s();
      Init.methods();
    },
    s: function (e) {
      (this._window = $(window)),
        (this._document = $(document)),
        (this._body = $("body")),
        (this._html = $("html"));
    },
    methods: function (e) {
      Init.w();
      Init.BackToTop();
      Init.preloader();
      Init.header();
      Init.inputImage();
      Init.slick();
      Init.categoryToggle();
      Init.countdownInit(".countdown", "2024/12/01");
      Init.formValidation();
      Init.contactForm();
    },
    w: function (e) {
      if (isMobile) {
        $("body").addClass("is-mobile");
      }
    },
    BackToTop: function () {
      var scrollToTopBtn = document.querySelector(".scrollToTopBtn");
      var rootElement = document.documentElement;
      function handleScroll() {
        var scrollTotal = rootElement.scrollHeight - rootElement.clientHeight;
        if (rootElement.scrollTop / scrollTotal > 0.05) {
          scrollToTopBtn.classList.add("showBtn");
        } else {
          scrollToTopBtn.classList.remove("showBtn");
        }
      }
      function scrollToTop() {
        rootElement.scrollTo({ top: 0, behavior: "smooth" });
      }
      scrollToTopBtn.addEventListener("click", scrollToTop);
      document.addEventListener("scroll", handleScroll);
    },
    preloader: function () {
      setTimeout(function () {
        $("#preloader").hide("slow");
      }, 3000);
    },
    header: function () {
      function dynamicCurrentMenuClass(selector) {
        let FileName = window.location.href.split("/").reverse()[0];
        selector.find("li").each(function () {
          let anchor = $(this).find("a");
          if ($(anchor).attr("href") == FileName) {
            $(this).addClass("current");
          }
        });
        selector.children("li").each(function () {
          if ($(this).find(".current").length) {
            $(this).addClass("current");
          }
        });
        if ("" == FileName) {
          selector.find("li").eq(0).addClass("current");
        }
      }
      if ($(".main-menu__list").length) {
        let mainNavUL = $(".main-menu__list");
        dynamicCurrentMenuClass(mainNavUL);
      }
      if ($(".main-menu__nav").length && $(".mobile-nav__container").length) {
        let navContent = document.querySelector(".main-menu__nav").innerHTML;
        let mobileNavContainer = document.querySelector(
          ".mobile-nav__container"
        );
        mobileNavContainer.innerHTML = navContent;
      }
      if ($(".sticky-header__content").length) {
        let navContent = document.querySelector(".main-menu").innerHTML;
        let mobileNavContainer = document.querySelector(
          ".sticky-header__content"
        );
        mobileNavContainer.innerHTML = navContent;
      }
      if ($(".mobile-nav__container .main-menu__list").length) {
        let dropdownAnchor = $(
          ".mobile-nav__container .main-menu__list .dropdown > a"
        );
        dropdownAnchor.each(function () {
          let self = $(this);
          let toggleBtn = document.createElement("BUTTON");
          toggleBtn.setAttribute("aria-label", "dropdown toggler");
          toggleBtn.innerHTML = "<i class='fa fa-angle-down'></i>";
          self.append(function () {
            return toggleBtn;
          });
          self.find("button").on("click", function (e) {
            e.preventDefault();
            let self = $(this);
            self.toggleClass("expanded");
            self.parent().toggleClass("expanded");
            self.parent().parent().children("ul").slideToggle();
          });
        });
      }
      if ($(".mobile-nav__toggler").length) {
        $(".mobile-nav__toggler").on("click", function (e) {
          e.preventDefault();
          $(".mobile-nav__wrapper").toggleClass("expanded");
          $("body").toggleClass("locked");
        });
      }
      $(window).on("scroll", function () {
        if ($(".stricked-menu").length) {
          var headerScrollPos = 130;
          var stricky = $(".stricked-menu");
          if ($(window).scrollTop() > headerScrollPos) {
            stricky.addClass("stricky-fixed");
          } else if ($(this).scrollTop() <= headerScrollPos) {
            stricky.removeClass("stricky-fixed");
          }
        }
      });
    },
    inputImage: function () {
      if ($(".input-block-2").length) {
        $("input[type=file]").change(function (e) {
          $(this)
            .parents(".uploadFile")
            .find(".filename")
            .text(e.target.files[0].name);
        });
      }
    },
    slick: function () {
      if ($(".hero-images-slider").length) {
        $(".hero-images-slider").slick({
          vertical: !0,
          verticalSwiping: !0,
          slidesToShow: 3,
          slidesToScroll: 1,
          autoplay: !0,
          centerMode: !0,
          autoplaySpeed: 0,
          speed: 8000,
          cssEase: "linear",
          infinite: !0,
          arrows: !1,
          touchMove: !0,
          swipeToSlide: !0,
          swipe: !0,
          responsive: [
            { breakpoint: 1799, settings: { slidesToShow: 2 } },
            {
              breakpoint: 1099,
              settings: { vertical: !1, verticalSwiping: !1, slidesToShow: 4 },
            },
            {
              breakpoint: 899,
              settings: { vertical: !1, verticalSwiping: !1, slidesToShow: 3 },
            },
            {
              breakpoint: 769,
              settings: { vertical: !1, verticalSwiping: !1, slidesToShow: 3 },
            },
            {
              breakpoint: 576,
              settings: { vertical: !1, verticalSwiping: !1, slidesToShow: 1 },
            },
          ],
        });
      }
      if ($(".testimonials-slider").length) {
        $(".testimonials-slider").slick({
          slidesToShow: 1,
          slidesToScroll: 1,
          infinite: !0,
          autoplay: !1,
          dots: !0,
          draggable: !0,
          arrows: !1,
          lazyLoad: "progressive",
          speed: 800,
          autoplaySpeed: 2000,
        });
      }
      if ($(".player-card-slider").length) {
        $(".player-card-slider").slick({
          slidesToShow: 3,
          slidesToScroll: 1,
          infinite: !0,
          autoplay: !1,
          dots: !0,
          draggable: !0,
          arrows: !1,
          lazyLoad: "progressive",
          speed: 500,
          autoplaySpeed: 2000,
          responsive: [
            { breakpoint: 1025, settings: { slidesToShow: 2 } },
            { breakpoint: 576, settings: { slidesToShow: 1 } },
          ],
        });
      }
      $(".btn-prev").click(function () {
        var $this = $(this).attr("data-slide");
        $("." + $this).slick("slickPrev");
      });
      $(".btn-next").click(function () {
        var $this = $(this).attr("data-slide");
        $("." + $this).slick("slickNext");
      });
    },
    categoryToggle: function () {
      if ($(".main-menu__right").length) {
        $(".profile").click(function (event) {
          event.stopPropagation();
          $(".menu").toggleClass("active");
        });
        $(document).click(function (event) {
          if (!$(event.target).closest(".profile, .menu").length) {
            $(".menu").removeClass("active");
          }
        });
        $(".menu").click(function (event) {
          event.stopPropagation();
        });
      }
    },
    countdownInit: function (countdownSelector, countdownTime, countdown) {
      var eventCounter = $(countdownSelector);
      if (eventCounter.length) {
        eventCounter.countdown(countdownTime, function (e) {
          $(this).html(
            e.strftime(
              "<li><h5>%D</h5><h6>Days</h6></li>\
                <li><h5>%H</h5><h6>Hrs</h6></li>\
                <li><h5>%M</h5><h6>Mins</h6></li>\
                <li><h5>%S</h5><h6>Secs</h6></li>"
            )
          );
        });
      }
    },
    formValidation: function () {
      if ($(".contact-form").length) {
        $(".contact-form").validate();
      }
      if ($(".signup-form").length) {
        $(".signup-form").validate();
      }
      if ($(".login-form").length) {
        $(".login-form").validate();
      }
      if ($(".register-form").length) {
        $(".register-form").validate();
      }
    },
    contactForm: function () {
      $(".contact-form").on("submit", function (e) {
        e.preventDefault();
        if ($(".contact-form").valid()) {
          var _self = $(this);
          _self
            .closest("div")
            .find('button[type="submit"]')
            .attr("disabled", "disabled");
          var data = $(this).serialize();
          $.ajax({
            url: "./assets/mail/contact.php",
            type: "post",
            dataType: "json",
            data: data,
            success: function (data) {
              $(".contact-form").trigger("reset");
              _self.find('button[type="submit"]').removeAttr("disabled");
              if (data.success) {
                document.getElementById("message").innerHTML =
                  "<h4 class='color-primary mt-16 mb-16'>Email Sent Successfully</h4>";
              } else {
                document.getElementById("message").innerHTML =
                  "<h4 class='color-primary mt-16 mb-16'>There is an error</h4>";
              }
              $("#messages").show("slow");
              $("#messages").slideDown("slow");
              setTimeout(function () {
                $("#messages").slideUp("hide");
                $("#messages").hide("slow");
              }, 4000);
            },
          });
        } else {
          return !1;
        }
      });
    },
  };
  Init.i();
})(window, document, jQuery);
