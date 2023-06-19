SUMMARY = "A small image just capable of allowing a device to boot."

INITRAMFS_IMAGE = "core-image-minimal-initramfs"

IMAGE_INSTALL = "packagegroup-core-boot ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"


initramfs_postprocess() {
  rm ${IMAGE_ROOTFS}/boot/bzImage-*
  cp ${DEPLOY_DIR_IMAGE}/bzImage-initramfs--* ${IMAGE_ROOTFS}/boot/bzImage
}

ROOTFS_POSTPROCESS_COMMAND += "initramfs_postprocess; "