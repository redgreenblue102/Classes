// DATA TO LOAD
var holidays;
var lectures;
var recitations;
var references;
var labs;
var hws;
var startingMondayDate;
var endingFridayDate;
var daysInMonth;
var isLeapYear;

function ScheduleItem(sDate, sTitle, sTopic, sLink) {
    this.date = sDate;
    this.title = sTitle;
    this.topic = sTopic;
    this.link = sLink;
}
function ScheduleDate(sMonth, sDay, sYear) {
    this.month = sMonth;
    this.day = sDay;
    this.year = sYear;
}
function buildSchedule() {
    var jsonFile = "./js/SiteData.json";
    $.getJSON(jsonFile, function (json) {
	loadJSONData(json);
        buildScheduleTable();
        addHolidays();
        addLectures();
        addRecitations();
        addLabs();
        addReferences();
        addHWs();
    });
}
function initDateData() {
    var currentYear = new Date().getFullYear();
    if (startingMondayDate)
        currentYear = startingMondayDate.year;
    daysInMonth = new Array();
    if ((currentYear % 4) === 0) {
        isLeapYear = true;
        daysInMonth[2] = 29;
    }
    else {
        isLeapYear = false;
        daysInMonth[2] = 28;
    }
    daysInMonth[1] = 31;
    daysInMonth[3] = 31;
    daysInMonth[4] = 30;
    daysInMonth[5] = 31;
    daysInMonth[6] = 30;
    daysInMonth[7] = 31;
    daysInMonth[8] = 31;
    daysInMonth[9] = 30;
    daysInMonth[10] = 31;
    daysInMonth[11] = 30;
    daysInMonth[12] = 31;
}
function loadJSONData(data) {
    // FIRST GET THE STARTING AND ENDING DATES
    var startingMondayMonth = parseInt(data.startingMondayMonth, 10);
    var startingMondayDay = parseInt(data.startingMondayDay, 10);
    var startingMondayYear = parseInt(data.startingMondayYear, 10);
    startingMondayDate = new ScheduleDate(startingMondayMonth, startingMondayDay, startingMondayYear);
    var endingFridayMonth = parseInt(data.endingFridayMonth, 10);
    var endingFridayDay = parseInt(data.endingFridayDay, 10);
    var endingFridayYear = parseInt(data.endingFridayYear, 10);
    endingFridayDate = new ScheduleDate(endingFridayMonth, endingFridayDay, endingFridayYear);

    // SETUP ALL THE OTHER DATE INFO
    initDateData();
    
    // THEN GET THE HOLIDAYS
    holidays = new Array();
    for (var i = 0; i < data.holidays.length; i++) {
        var holidayData = data.holidays[i];
        var holidayDate = new ScheduleDate(holidayData.month, holidayData.day, holidayData.year);
        var holiday = new ScheduleItem(holidayDate, holidayData.title, "none", holidayData.link);
        holidays[i] = holiday;
    }
    
    // AND THEN THE LECTURES
    lectures = new Array();
    for (var i = 0; i < data.lectures.length; i++) {
        var lectureData = data.lectures[i];
        var lectureDate = new ScheduleDate(lectureData.month, lectureData.day, lectureData.year);
        var lecture = new ScheduleItem(lectureDate, lectureData.title, lectureData.topic, lectureData.link);
        lectures[i] = lecture;
    }
    
    // AND THEN THE REFERENCES
    references = new Array();
    for (var i = 0; i < data.references.length; i++) {
        var refData = data.references[i];
        var refDate = new ScheduleDate(refData.month, refData.day, refData.year);
        var ref = new ScheduleItem(refDate, refData.title, refData.topic, refData.link);
        references[i] = ref;
    }
    
    // AND THEN THE RECITATIONS
    recitations = new Array();
    for (var i = 0; i < data.recitations.length; i++) {
        var recData = data.recitations[i];
        var recDate = new ScheduleDate(recData.month, recData.day, recData.year);
        var rec = new ScheduleItem(recDate, recData.title, recData.topic, "none");
        recitations[i] = rec;
    }
    
    // AND THEN THE LABS
    labs = new Array();
    for (var i = 0; i < data.labs.length; i++) {
        var labData = data.labs[i];
        var labDate = new ScheduleDate(labData.month, labData.day, labData.year);
        var lab = new ScheduleItem(labDate, labData.title, labData.topic, "none");
        labs[i] = lab;
    }
    
    // AND THEN THE HWs
    hws = new Array();
    for (var i = 0; i < data.hws.length; i++) {
        var hwData = data.hws[i];
        var hwDate = new ScheduleDate(hwData.month, hwData.day, hwData.year);
        var hw = new ScheduleItem(hwDate, hwData.title, hwData.topic, hwData.link);
        hws[i] = hw;
    }
}
function buildScheduleTable() {
    var countMonth = startingMondayDate.month;
    var countDay = startingMondayDate.day;
    var countYear = startingMondayDate.year;
    var countDate = new ScheduleDate(countMonth, countDay, countYear);
    var table = $("#schedule_table");
    while (firstDateIsBeforeSecond(countDate, endingFridayDate)) {
        table.append(
                  "<tr>"
                + "<th class=\"sch\">MONDAY</th>"
                + "<th class=\"sch\">TUESDAY</th>"
                + "<th class=\"sch\">WEDNESDAY</th>"
                + "<th class=\"sch\">THURSDAY</th>"
                + "<th class=\"sch\">FRIDAY</th>"
                + "</tr>");
        table.append("<tr>");
        for (var i = 0; i < 5; i++) {
            table.append(
                    "<td class=\"sch\" id=\"" + countDate.month + "_" + countDate.day + "\"><strong>" + countDate.month + "/" + countDate.day + "</strong><br /></td>");
            incDate(countDate);
        }
        table.append("</tr>");
        incDate(countDate);
        incDate(countDate);
    }
}
function incDate(dateToInc) {
    dateToInc.day++;
    var maxDays = daysInMonth[dateToInc.month];
    if (dateToInc.day > maxDays) {
        dateToInc.day = 1;
        dateToInc.month++;
    }
}
function firstDateIsBeforeSecond(firstDate, secondDate) {
    if (firstDate.month < secondDate.month)
        return true;
    if ((firstDate.month === secondDate.month)
        && (firstDate.day < secondDate.day))
        return true;
    return false;
}
function addHolidays() {
    for (var i = 0; i < holidays.length; i++) {
        var holiday = holidays[i];
        var cell = $("#" + holiday.date.month + "_" + holiday.date.day);
        cell.addClass("holiday");
        cell.append(
                "<a href=\"" + holiday.link + "\">" + holiday.title + "</a><br /><br />"
                );
    }
}
function addLectures() {
    for (var i = 0; i < lectures.length; i++) {
        var lecture = lectures[i];
        var textToAppend = "" + lecture.topic;
        if (lecture.link.valueOf() != "none".valueOf()) {
            textToAppend = "<a href=\"" + lecture.link + "\">"
                          + textToAppend
                          + "</a>";
        }
        textToAppend = "<span class=\"lecture\">"
                       + lecture.title + "<br />"
                       + "</span>"
                       + textToAppend;
        var cell = $("#" + lecture.date.month + "_" + lecture.date.day);
        textToAppend += "<br /><br />";
        cell.append(textToAppend);
    }    
}
function addReferences() {
    for (var i = 0; i < references.length; i++) {
        var ref = references[i];
        var textToAppend = "<span class=\"tutorial\">" + ref.title + "</span><br />";

        if (ref.link.valueOf() != "none".valueOf()) {
            textToAppend += "<a href=\"" + ref.link + "\">"
                            + ref.topic + "</a>";
        }
        else
            textToAppend += ref.topic;

        textToAppend += "<br /><br />";
        var cell = $("#" + ref.date.month + "_" + ref.date.day);
        textToAppend += "<br />";
        cell.append(textToAppend);
    }       
}
function addRecitations() {
    for (var i = 0; i < recitations.length; i++) {
        var rec = recitations[i];
        var textToAppend = "<span class=\"tutorial\">"
                + rec.title
                + "</span><br />"
                + rec.topic;
        var cell = $("#" + rec.date.month + "_" + rec.date.day);
        textToAppend += "<br />";
        cell.append(textToAppend);
    }     
}
function addLabs() {
    for (var i = 0; i < labs.length; i++) {
        var lab = labs[i];
        var textToAppend = "<span class=\"tutorial\">"
                + lab.title
                + "</span><br />"
                + lab.topic;
        var cell = $("#" + lab.date.month + "_" + lab.date.day);
        textToAppend += "<br />";
        cell.append(textToAppend);
    }     
}
function addHWs() {
    for (var i = 0; i < hws.length; i++) {
        var hw = hws[i];
        var textToAppend = hw.title;
        if (hw.link.valueOf() == "none".valueOf()) {
            textToAppend = 
                "<span class=\"hw\">"
                + textToAppend
                + "</span><br />";
        }
        else {
            textToAppend =
                "<a href=\"" + hw.link + "\">"
                + textToAppend
                + "</a><br />";
        }
        textToAppend += hw.topic + "<br /><br />";
        var cell = $("#" + hw.date.month + "_" + hw.date.day);
        textToAppend += "<br />";
        cell.append(textToAppend);
    }
}