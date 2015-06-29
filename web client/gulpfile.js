var gulp = require('gulp');

var babel = require("gulp-babel");


function getJSSourceFiles(){
    return ['src/js/**/*.js'];
}

gulp.task('build-scripts',function(){
    return gulp.src(getJSSourceFiles())
        .pipe(babel())
        .pipe(gulp.dest('build/js/'));
});

gulp.task('default', function() {
  // place code for your default task here
});
