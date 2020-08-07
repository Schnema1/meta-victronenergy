DESCRIPTION = "DBusrecorder"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit ve_package
inherit python-compile

RDEPENDS_${PN} = "bash python3-core python3-dbus"

SRC_URI = "git://github.com/victronenergy/dbus-recorder.git;protocol=https;branch=p3"
S = "${WORKDIR}/git"
SRCREV = "69cedf2f02d1e9ac40cb028ec57b02b8b6802b41"

do_install () {
    install -d ${D}/${bindir}

    install -m 0755 ${S}/play.sh ${D}/${bindir}
    install -m 0755 ${S}/startdemo.sh ${D}/${bindir}
    install -m 0755 ${S}/stopdemo.sh ${D}/${bindir}

    # copy python scripts
    install -m 755 -D ${S}/*.py ${D}/${bindir}
    # copy data files
    install -m 444 -D ${S}/*.dat ${D}/${bindir}
}
