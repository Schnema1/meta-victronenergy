SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree rtl8723bu"

KERNEL_CONFIG_COMMAND = "make -C ${S} O=${B} ARCH=arm bbb_defconfig"

KERNEL_DEVICETREE = " \
    am335x-boneblack.dtb \
    bbb-venus.dtb \
    bbb-octo-venus.dtb \
    bbe-venus.dtb \
"

LINUX_VERSION = "4.9"
LINUX_VERSION_EXTENSION = "-venus"

inherit kernel

S = "${WORKDIR}/linux-${PV}"
B = "${WORKDIR}/build"

SRC_URI = "https://github.com/victronenergy/linux/archive/v${PV}.tar.gz \
    file://0001-Leave-CONFIG_ADVISE_SYSCALLS-to-its-default-enabled.patch \
"

SRC_URI[md5sum] = "a58427fcb954ad25f0695942d30a051a"
SRC_URI[sha256sum] = "446cf076aafd1f0639efd5b339695bb7cbf8ada4f8f7532b7edec56328e7f0e4"
