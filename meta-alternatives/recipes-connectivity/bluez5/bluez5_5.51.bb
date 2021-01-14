require recipes-connectivity/bluez5/bluez5.inc

REQUIRED_DISTRO_FEATURES = "bluez5"

SRC_URI[md5sum] = "a2f269c9f9a943394043ac1de69eb5b0"
SRC_URI[sha256sum] = "ebedfb359f62957940822f1d0b39fcee30422380e435608dad06bb3913d5ebba"
FILESEXTRAPATHS_prepend := "${THISDIR}/../../../../openembedded-core/meta/recipes-connectivity/bluez5/bluez5:"
SRC_URI_remove = "file://0001-Allow-using-obexd-without-systemd-in-the-user-sessio.patch"

# FIXME when switching to zeus
SRC_URI_remove += "file://CVE-2018-10910.patch"
SRC_URI_remove += "file://gcc9-fixes.patch"
SRC_URI_remove += "file://0001-tools-Fix-build-after-y2038-changes-in-glibc.patch"

# noinst programs in Makefile.tools that are conditional on READLINE
# support
NOINST_TOOLS_READLINE ?= " \
    ${@bb.utils.contains('PACKAGECONFIG', 'deprecated', 'attrib/gatttool', '', d)} \
    tools/obex-client-tool \
    tools/obex-server-tool \
    tools/bluetooth-player \
    tools/obexctl \
    tools/btmgmt \
"

# noinst programs in Makefile.tools that are conditional on TESTING
# support
NOINST_TOOLS_TESTING ?= " \
    emulator/btvirt \
    emulator/b1ee \
    emulator/hfp \
    peripheral/btsensor \
    tools/3dsp \
    tools/mgmt-tester \
    tools/gap-tester \
    tools/l2cap-tester \
    tools/sco-tester \
    tools/smp-tester \
    tools/hci-tester \
    tools/rfcomm-tester \
    tools/bnep-tester \
    tools/userchan-tester \
"

# noinst programs in Makefile.tools that are conditional on TOOLS
# support
NOINST_TOOLS_BT ?= " \
    tools/bdaddr \
    tools/avinfo \
    tools/avtest \
    tools/scotest \
    tools/amptest \
    tools/hwdb \
    tools/hcieventmask \
    tools/hcisecfilter \
    tools/btinfo \
    tools/btsnoop \
    tools/btproxy \
    tools/btiotest \
    tools/bneptest \
    tools/mcaptest \
    tools/cltest \
    tools/oobtest \
    tools/advtest \
    tools/seq2bseq \
    tools/nokfw \
    tools/create-image \
    tools/eddystone \
    tools/ibeacon \
    tools/btgatt-client \
    tools/btgatt-server \
    tools/test-runner \
    tools/check-selftest \
    tools/gatt-service \
    profiles/iap/iapd \
    ${@bb.utils.contains('PACKAGECONFIG', 'btpclient', 'tools/btpclient', '', d)} \
"

do_install_append() {
    rm -rf ${D}/usr/share/zsh
}
