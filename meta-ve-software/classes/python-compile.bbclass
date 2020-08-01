DEPENDS += "python3-native"

do_install_append() {
    # force everybody to use python3 for testing. Should be removed ofcourse.
    find ${D} -exec sed -i -e 's,#!\s*/usr/bin/python2\( .*\|$\),#!/usr/bin/python3\1,' -e 's,#!\s*/usr/bin/python\( .*\|$\),#!/usr/bin/python3\1,' {} \;
    #nativepython3 -m compileall ${D}
}
