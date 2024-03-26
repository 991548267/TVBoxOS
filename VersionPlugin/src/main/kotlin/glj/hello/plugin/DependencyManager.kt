package glj.hello.plugin

/**
 *
 * @author: 99154
 * @description 依赖管理
 * @date: Create in 16:09 2022/6/23
 */

object BuildGradle { // 未使用请注意跟项目build.gradle保持一致
    private const val version_gradle = "7.0.2"
    private const val version_kotlin = "1.6.21"

    const val plugin_gradle = "com.android.tools.build:gradle:${version_gradle}"
    const val plugin_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${version_kotlin}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${version_kotlin}"
    const val kotlin_stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${version_kotlin}"
    const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version_kotlin}"
}

object Jetpack {
    private const val version_activity = "1.4.0"
    private const val version_annotation = "1.7.0"
    private const val version_appcompat = "1.3.0"
    private const val version_camera = "1.2.0-alpha02"
    private const val version_constraintlayout = "2.0.4"
    private const val version_constraintlayout_compose = "1.0.1"
    private const val version_core = "1.5.0"
    private const val version_fragment = "1.4.1"
    private const val version_lifecycle = "2.2.0"
    private const val version_material = "1.4.0"
    private const val version_multidex = "2.0.1"
    private const val version_swiperefreshlayout = "1.1.0"
    private const val version_recyclerview = "1.2.1"
    private const val version_recyclerview_selection = "1.1.0"
    private const val version_room = "2.3.0"

    const val activity = "androidx.activity:activity:$version_activity"
    const val activity_ktx = "androidx.activity:activity-ktx:$version_activity"

    const val annotation = "androidx.annotation:annotation:${version_annotation}"

    const val appcompat = "androidx.appcompat:appcompat:${version_appcompat}"
    const val appcompat_resources = "androidx.appcompat:appcompat-resources:${version_appcompat}"

    const val camera_core = "androidx.camera:camera-core:${version_camera}"
    const val camera_camera2 = "androidx.camera:camera-camera2:${version_camera}"
    const val camera_lifecycle = "androidx.camera:camera-lifecycle:${version_camera}"
    const val camera_video = "androidx.camera:camera-video:${version_camera}"
    const val camera_view = "androidx.camera:camera-view:${version_camera}"
    const val camera_mlkit_vision = "androidx.camera:camera-mlkit-vision:${version_camera}"
    const val camera_extensions = "androidx.camera:camera-extensions:${version_camera}"

    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${version_constraintlayout}"
    const val constraintlayout_compose = "androidx.constraintlayout:constraintlayout-compose:${version_constraintlayout_compose}"

    const val core_ktx = "androidx.core:core-ktx:${version_core}"

    const val fragment = "androidx.fragment:fragment:${version_fragment}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${version_fragment}"
    const val fragment_testing = "androidx.fragment:fragment-testing:${version_fragment}"

    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${version_lifecycle}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${version_lifecycle}"
    const val lifecycle_viewmodel_compose = "androidx.lifecycle:lifecycle-viewmodel-compose:${version_lifecycle}"
    const val lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${version_lifecycle}"
    const val lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${version_lifecycle}"
    const val lifecycle_viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${version_lifecycle}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${version_lifecycle}"
    const val lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:${version_lifecycle}"
    const val lifecycle_service = "androidx.lifecycle:lifecycle-service:${version_lifecycle}"
    const val lifecycle_process = "androidx.lifecycle:lifecycle-process:${version_lifecycle}"
    const val lifecycle_reactivestreams_ktx = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${version_lifecycle}"

    const val material = "com.google.android.material:material:${version_material}"

    const val multidex = "androidx.multidex:multidex:${version_multidex}"

    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${version_swiperefreshlayout}"

    const val recyclerview = "androidx.recyclerview:recyclerview:${version_recyclerview}"
    const val recyclerview_selection = "androidx.recyclerview:recyclerview-selection:${version_recyclerview_selection}"

    const val room_ktx = "androidx.room:room-ktx:${version_room}"
    const val room_compiler = "androidx.room:room-compiler:${version_room}"
    const val room_runtime = "androidx.room:room-runtime:${version_room}"
    const val room_rxjava2 = "androidx.room:room-rxjava2:${version_room}"
    const val room_testing = "androidx.room:room-testing:${version_room}"
}

object Jetbrains {
    private const val version_anko = "0.10.8"
    private const val version_kotlin = "1.4.31" // 未使用请注意跟项目build.gradle保持一致

    const val anko_common = "org.jetbrains.anko:anko-common:${version_anko}"
    const val anko_sqlite = "org.jetbrains.anko:anko-sqlite:${version_anko}"
    const val anko_coroutines = "org.jetbrains.anko:anko-coroutines:${version_anko}"
    const val anko_design = "org.jetbrains.anko:anko-design:${version_anko}"

    const val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${version_kotlin}"
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${version_kotlin}"
    const val kotlin_stdlib_jdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${version_kotlin}"
    const val kotlin_stdlib_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${version_kotlin}"
    const val kotlin_test_junit = "org.jetbrains.kotlin:kotlin-test-junit:${version_kotlin}"
}

object ThirdPart {
    private const val version_airbnb_lottie = "3.6.0"
    private const val version_alibaba_oss = "2.9.11"
    private const val version_alibaba_push = "3.7.7"
    private const val version_alibaba_router = "1.5.1"
    private const val version_glide = "4.11.0"
    private const val version_jackson = "2.11.0"
    private const val version_koin = "2.1.5"
    private const val version_leakcanary = "2.9.1"
    private const val version_logger = "2.2.0"
    private const val version_okhttp3 = "4.9.3"
    private const val version_retrofit2 = "2.9.0"
    private const val version_bga_qrcode_zxing = "1.3.7"
    private const val version_bga_banner = "2.2.6@aar"
    private const val version_exoplayer = "2.15.1"
    private const val version_dkplayer = "3.3.5"

    const val airbnb_lottie = "com.airbnb.android:lottie:${version_airbnb_lottie}"

    // 阿里推送
    const val alibaba_oss_android = "com.aliyun.dpa:oss-android-sdk:${version_alibaba_oss}"
    const val alibaba_push = "com.aliyun.ams:alicloud-android-push:${version_alibaba_push}"
    const val alibaba_third_push = "com.aliyun.ams:alicloud-android-third-push:${version_alibaba_push}"
    const val alibaba_third_push_huawei = "com.aliyun.ams:alicloud-android-third-push-huawei:${version_alibaba_push}"
    const val alibaba_third_push_xiaomi = "com.aliyun.ams:alicloud-android-third-push-xiaomi:${version_alibaba_push}"
    const val alibaba_third_push_oppo = "com.aliyun.ams:alicloud-android-third-push-oppo:${version_alibaba_push}"
    const val alibaba_third_push_vivo = "com.aliyun.ams:alicloud-android-third-push-vivo:${version_alibaba_push}"
    const val alibaba_third_push_meizu = "com.aliyun.ams:alicloud-android-third-push-meizu:${version_alibaba_push}"
    const val alibaba_third_push_fcm = "com.aliyun.ams:alicloud-android-third-push-fcm:${version_alibaba_push}"

    // 阿里路由
    const val alibaba_router_api = "com.alibaba:arouter-api:${version_alibaba_router}"
    const val alibaba_router_compiler = "com.alibaba:arouter-compiler:${version_alibaba_router}"

    const val glide_glide = "com.github.bumptech.glide:glide:${version_glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${version_glide}"
    const val glide_okhttp3_integration = "com.github.bumptech.glide:okhttp3-integration:${version_glide}"

    // Jackson Json解析
    const val jackson_core = "com.fasterxml.jackson.core:jackson-core:${version_jackson}"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:${version_jackson}"
    const val jackson_annotations = "com.fasterxml.jackson.core:jackson-annotations:${version_jackson}"

    // Koin依赖注入
    const val koin_core = "org.koin:koin-core:${version_koin}"
    const val koin_android = "org.koin:koin-android:${version_koin}"
    const val koin_android_scope = "org.koin:koin-android-scope:$${version_koin}"
    const val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${version_koin}"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${version_leakcanary}"

    // https://mvnrepository.com/artifact/com.orhanobut/logger
    const val logger = "com.orhanobut:logger:${version_logger}"

    // https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${version_okhttp3}"
    const val retrofit2_retrofit = "com.squareup.retrofit2:retrofit:${version_retrofit2}"
    const val retrofit2_adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${version_retrofit2}"
    const val retrofit2_converter_gson = "com.squareup.retrofit2:converter-gson:${version_retrofit2}"
    const val retrofit2_retrofit_mock = "com.squareup.retrofit2:retrofit-mock:${version_retrofit2}"

    const val bga_qrcode_zxing = "cn.bingoogolapple:bga-qrcode-zxing:${version_bga_qrcode_zxing}"
    const val bga_banner = "cn.bingoogolapple:bga-banner:${version_bga_banner}"

    // ExoPlayer
    const val exoplayer_core = "com.google.android.exoplayer:exoplayer-core:${version_exoplayer}"
    const val exoplayer_dash = "com.google.android.exoplayer:exoplayer-dash:${version_exoplayer}"
    const val exoplayer_hls = "com.google.android.exoplayer:exoplayer-hls:${version_exoplayer}"
    const val exoplayer_rtsp = "com.google.android.exoplayer:exoplayer-rtsp:${version_exoplayer}"
    const val exoplayer_rtmp = "com.google.android.exoplayer:extension-rtmp:${version_exoplayer}"
    const val exoplayer_okhttp = "com.google.android.exoplayer:extension-okhttp:${version_exoplayer}"

    // dkplayer
    const val dkplayer_ui = "xyz.doikki.android.dkplayer:dkplayer-ui:${version_dkplayer}"
}

object Test {
    private const val version_junit = "4.13.2"
    private const val version_test = "1.3.0"
    private const val version_test_ext = "1.1.2"
    private const val version_test_espresso = "3.3.0"
    private const val version_robolectric = "4.8.1"

    const val junit = "junit:junit:${version_junit}"

    const val test_core = "androidx.test:core:${version_test}"
    const val test_core_ktx = "androidx.test:core-ktx:${version_test}"
    const val test_runner = "androidx.test:runner:${version_test}"
    const val test_orchestrator = "androidx.test:orchestrator:${version_test}"

    const val test_espresso_core = "androidx.test.espresso:espresso-core:${version_test_espresso}"

    const val test_ext_junit = "androidx.test.ext:junit:${version_test_ext}"
    const val test_ext_junit_ktx = "androidx.test.ext:junit_ktx:${version_test_ext}"
    const val test_ext_truth = "androidx.test.ext:truth:${version_test_ext}"

    // 单元测试框架
    const val robolectric = "org.robolectric:robolectric:${version_robolectric}"
}
