DESCRIPTION = "Localsettings python scripts"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit ve_package
inherit daemontools
inherit python-compile

SRC_URI = " \
    gitsm://github.com/victronenergy/localsettings.git;protocol=https;branch=p3 \
    file://com.victronenergy.settings.conf \
"
SRCREV = "a7902b60f94b14c8bdb6b23773a3c4069c9d00e6"
S = "${WORKDIR}/git"

DAEMONTOOLS_SERVICE_DIR = "${bindir}/service"
DAEMONTOOLS_RUN = "softlimit -d 100000000 -s 1000000 -a 100000000 ${bindir}/${PN}.py --path=/data/conf"

RDEPENDS_${PN} += " \
    python3-core \
    python3-dbus \
    python3-lxml \
"

do_install () {
    install -d ${D}${bindir}
    install -m 755 -D ${S}/*.py ${D}${bindir}

    install -d ${D}/${sysconfdir}/dbus-1/system.d
    install -m 644 ${WORKDIR}/com.victronenergy.settings.conf ${D}/${sysconfdir}/dbus-1/system.d
}
