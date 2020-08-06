LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9b0a9609befce3122afcc444da0fe825"

inherit ve_package
inherit daemontools
inherit python-compile

SRC_URI = " \
    gitsm://github.com/victronenergy/dbus_pump.git;protocol=https;branch=p3 \
    file://com.victronenergy.pump.conf \
"
SRCREV = "5b8e6f872bebd3cf961987f18e58b3d5c5edac09"
PR = "r1"
S = "${WORKDIR}/git"

RDEPENDS_${PN} = " \
    localsettings \
    python3-core \
    python3-datetime \
    python3-dbus \
    python3-json \
    python3-pygobject \
"

DAEMONTOOLS_SERVICE_DIR = "${bindir}/service"
DAEMONTOOLS_RUN = "softlimit -d 100000000 -s 1000000 -a 100000000 ${bindir}/dbus_pump.py"
DAEMONTOOLS_DOWN = "1"

do_install () {
    install -d ${D}${bindir}
    cp -r ${S}/* ${D}${bindir}

    install -d ${D}/${sysconfdir}/dbus-1/system.d
    install -m 644 ${WORKDIR}/com.victronenergy.pump.conf ${D}/${sysconfdir}/dbus-1/system.d
}
