# build but not installed in the image
inherit packagegroup
LICENSE = "MIT"

DEPENDS += "\
    devmem2 \
    gdb \
    gpsd \
    git \
    lsof \
    nodejs \
    ntp \
    openvpn \
    packagegroup-core-buildessential \
    packagegroup-replace-busybox \
    python3-pylint \
    s6 \
    tcpdump \
    tinymembench \
    tmux \
    valgrind \
    venus-socketcan-test \
    vim \
    x11vnc \
"
# FIXME: perf

# Add as optional packages until we include python3 proper
DEPENDS += "\
    python3-requests \
    python3-lxml \
    python3-paho-mqtt \
    python3-pymodbus \
"
