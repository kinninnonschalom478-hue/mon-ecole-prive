#!/data/data/com.termux/files/usr/bin/bash
set -e
cd "$(dirname "$0")"
./gradlew :apps:parent:assembleDebug :apps:teacher:assembleDebug :apps:admin:assembleDebug
mkdir -p dist
cp apps/parent/build/outputs/apk/debug/app-parent-debug.apk dist/MON_ECOLE_PARENT.apk
cp apps/teacher/build/outputs/apk/debug/app-teacher-debug.apk dist/MON_ECOLE_ENSEIGNANT.apk
cp apps/admin/build/outputs/apk/debug/app-admin-debug.apk dist/MON_ECOLE_ADMIN.apk
ls -lh dist/*.apk
