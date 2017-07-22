/**
 * Created by Chris on 7/19/2017.
 */

var today = new Date();
var expiry = new Date(today.getTime() + 30 * 24 * 3600 * 1000); // plus 30 days
var expired = new Date(today.getTime() - 24 * 3600 * 1000); // less 24 hours

function setCookie(name, value) {
    document.cookie=name + "=" + escape(value) + "; path=/; expires=" + expiry.toGMTString();
}

function getCookie(name) {
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
}

function deleteCookie(name) {
    document.cookie=name + "=null; path=/; expires=" + expired.toGMTString();
}

function storeValues(parameters)
{
    setCookie("accessKeyId",         document.myForm.accessKeyId.value);
    setCookie("secretKeyId",         document.myForm.secretKeyId.value);
    setCookie("bucket",              document.myForm.bucket.value);
    setCookie("expirationInMinutes", document.myForm.expirationInMinutes.value);
    setCookie("files",               document.myForm.files.value);
    return true;
}

function restoreValues(form)
{
    if(accessKeyId = getCookie("accessKeyId")) document.myForm.accessKeyId.value = accessKeyId;
    if(secretKeyId = getCookie("secretKeyId")) document.myForm.secretKeyId.value = secretKeyId;
    if(bucket = getCookie("bucket"))           document.myForm.bucket.value = bucket;
    if(expirationInMinutes = getCookie("expirationInMinutes")) document.myForm.expirationInMinutes.value = expirationInMinutes;
    if(files = getCookie("files"))             document.myForm.files.value = files;
}

function clearValues()
{
    deleteCookie("accessKeyId");
    deleteCookie("secretKeyId");
    deleteCookie("bucket");
    deleteCookie("expirationInMinutes");
    deleteCookie("files");
    alert('Your cookies have been deleted!');
}

