DESCRIPTION = "Node-RED"
HOMEPAGE = "http://nodered.org"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34f8c1142fd6208a8be89399cb521df9"

RDEPENDS_${PN} += "\
	bash \
"

SRC_URI = "\
	https://registry.npmjs.org/signalk-server/-/${PN}-${PV}.tgz;unpack=0 \
	file://start-signalk.sh \
	file://settings.json \
	file://logo.svg \
	file://venus.json \
	file://defaults.json \
"

SRC_URI[md5sum] = "ba7f782cd32e7853d3bb7a985e40e22c"
SRC_URI[sha256sum] = "40132f35b744c5f6993679ef79a141b4b038ea4783a8fde3cd1718dfd42fcd05"

inherit npmve
inherit daemontools

DAEMON_PN = "${PN}"
DAEMONTOOLS_SERVICE_DIR = "${sysconfdir}/${PN}/service"
DAEMONTOOLS_SCRIPT = "HOME=/home/root exec ${bindir}/signalk-server"
DAEMONTOOLS_DOWN = "1"
DAEMONTOOLS_LOG_DIR = "${DAEMONTOOLS_LOG_DIR_PREFIX}/signalk-server"

NPM_INSTALLDIR = "${D}${libdir}/node_modules/${PN}"

do_install_append() {

	mkdir ${D}${bindir}
	install -m 0755 ${WORKDIR}/start-signalk.sh ${D}${bindir}/signalk-server

	# this folder keeps the default settings. start-signalk.sh copies them
	# to the data partition on first boot.
	install -d ${NPM_INSTALLDIR}/defaults
	install -m 0755 ${WORKDIR}/settings.json ${NPM_INSTALLDIR}/defaults
	install -m 0755 ${WORKDIR}/defaults.json ${NPM_INSTALLDIR}/defaults
	install -m 0755 ${WORKDIR}/venus.json ${NPM_INSTALLDIR}/defaults
	install -m 0755 ${WORKDIR}/logo.svg ${NPM_INSTALLDIR}/defaults

	(cd ${NPM_INSTALLDIR}; npm --arch=${NPM_ARCH} --target_arch=${NPM_ARCH} install signalk-venus-plugin@1.20.0)

	# remove the files in put/test: they are compiled, though not cross-compiled thus
	# giving QA errors as well as being useless; and also they are not necessary
	rm -rf ${NPM_INSTALLDIR}/node_modules/put/test
}

