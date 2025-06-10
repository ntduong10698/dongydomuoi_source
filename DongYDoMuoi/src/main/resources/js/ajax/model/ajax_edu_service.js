const URL_EDU_SERIVCE = 'edu-service/api/';

//CHAPTER_API
function chapterFindById(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/chapters/${id}`);
}

function chapterFindByCourse(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/chapters/course/${id}`);
}
//END_CHAPTER_API

//COURSE_API
function courseFindById(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/courses/${id}`);
}

function courseActive(form) {
    return ajaxPost(`${URL_EDU_SERIVCE}v1/public/courses/active`, form);
}

function courseFindByLecturer(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/courses/lecturer/${id}`);
}

function courseFindByIds(ids) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/courses/list?ids=${ids}`);
}

function courseUserFindByUser() {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/user/courses`);
}
//END_COURSE_API

//LECTURER_API
function lecturerFindById(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/lecturers/${id}`);
}

function lecturerFindByCompany(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/lecturers/company/${id}`);
}
//END_LECTURER_API

//LESSON_API
function lessonFindByCourse(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/lessons/course/${id}`);
}

function lessonUserFindById(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/user/lessons/${id}`);
}

function lessonUserNextLesson(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/user/lessons/${id}/next`);
}

function lessonUserPreviousLesson(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/user/lessons/${id}/previous`);
}

function lessonUserLearn(id) {
    return ajaxPut(`${URL_EDU_SERIVCE}v1/user/lessons/${id}`);
}

function lessonUserFindByCourse(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/user/lessons/course/${id}`)
}
//END_LESSON_API

//QUESTION_API
function questionFindById(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/questions/${id}`);
}

function questionFindByLesson(id) {
    return ajaxGet(`${URL_EDU_SERIVCE}v1/public/questions/lesson/${id}`);
}
//END_QUESTION_API