/*godteam-js:compressed at Thu Jul 11 21:36:18 CST 2013, use 32ms */(function(e,c){function j(b){var a;if(!b.documentShived){if(f.shivCSS&&!h){a=b.createElement("p");var d=b.getElementsByTagName("head")[0]||b.documentElement;a=!!(a.innerHTML="x<style>article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}audio{display:none}canvas,video{display:inline-block;*display:inline;*zoom:1}[hidden]{display:none}audio[controls]{display:inline-block;*display:inline;*zoom:1}mark{background:#FF0;color:#000}</style>",d.insertBefore(a.lastChild,d.firstChild))}if(!k){var c=
{},g=b.createElement;a=b.createDocumentFragment;var e=a();b.createElement=function(a){if(!f.shivMethods)return g(a);var b;return c[a]?b=c[a].cloneNode():l.test(a)?b=(c[a]=g(a)).cloneNode():b=g(a),b.canHaveChildren&&!m.test(a)?e.appendChild(b):b};a=Function;d=f.elements;d="string"==typeof d?d.split(" "):d;b.createDocumentFragment=a("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+d.join().replace(/\w+/g,function(a){return g(a),e.createElement(a),'c("'+a+'")'})+");return n}")(f,
e);a=!0}b=(a&&(b.documentShived=a),b)}return b}var i=e.html5||{},m=/^<|^(?:button|form|map|select|textarea|object|iframe|option|optgroup)$/i,l=/^<|^(?:a|b|button|code|div|fieldset|form|h1|h2|h3|h4|h5|h6|i|iframe|img|input|label|li|link|ol|option|p|param|q|script|select|span|strong|style|table|tbody|td|textarea|tfoot|th|thead|tr|ul)$/i,h,k,a=c.createElement("a");a.innerHTML="<xyz></xyz>";(h="hidden"in a)&&"function"==typeof injectElementWithStyles&&injectElementWithStyles("#modernizr{}",function(a){a.hidden=
!0;h="none"==(e.getComputedStyle?getComputedStyle(a,null):a.currentStyle).display});if(!(a=1==a.childNodes.length))a:{try{c.createElement("a")}catch(n){a=!0;break a}a=c.createDocumentFragment();a="undefined"==typeof a.cloneNode||"undefined"==typeof a.createDocumentFragment||"undefined"==typeof a.createElement}k=a;var f={elements:i.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",shivCSS:!1!==
i.shivCSS,shivMethods:!1!==i.shivMethods,type:"default",shivDocument:j};e.html5=f;j(c)})(this,document);
