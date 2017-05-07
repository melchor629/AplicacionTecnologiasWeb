/* Melchor Alejo Garau Madrigal */
const gulp = require('gulp');
const sass = require('gulp-sass');
const sourcemaps = require('gulp-sourcemaps');

const sassPath = './web/assets/sass/*.scss';

gulp.task('sass', () => {
    return gulp.src(sassPath)
        .pipe(sourcemaps.init())
        .pipe(sass({outputStyle: 'compressed'}).on('error', sass.logError))
        .pipe(sourcemaps.write('./maps'))
        .pipe(gulp.dest('./web/assets/css'));
});

gulp.task('sass:watch', () => {
    gulp.watch(sassPath, ['sass']);
});
