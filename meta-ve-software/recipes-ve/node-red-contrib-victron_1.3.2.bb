SUMMARY = "Victron Venus D-Bus plugin for node-red"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d7725b8b5e691085738d564afb312302"

#S = "${WORKDIR}/${PN}-${PV}"

NPM_ORG="victronenergy"

RDEPENDS_${PN} += "\
	node-red \
"

SRC_URI = "\
	https://registry.npmjs.org/@${NPM_ORG}/${PN}/-/${PN}-${PV}.tgz;unpack=0 \
	file://npm-shrinkwrap.json \
"

SRC_URI[md5sum] = "3c4091e5d125e66287712f839f850a5b"
SRC_URI[sha256sum] = "ea30d1bf1ef25eefaa8454897bbbc880c97e474bba817ff00876d4c5668528f7"

inherit npmve

NPM_INSTALLDIR = "${D}${libdir}/node_modules/@${NPM_ORG}/${PN}"

do_install_append() {
        # Remove hardware specific files
	rm -r ${NPM_INSTALLDIR}/node_modules/put/test
}
