let TimeUtils = {

    formatTime: function (data) {
        let time = {d: null, m: null};
        let dataInDate = new Date(data);
        time.d = dataInDate.getUTCDate() < 10 ? '0' + dataInDate.getUTCDate() : dataInDate.getUTCDate();
        time.m = 'th' + (dataInDate.getUTCMonth() + 1);
        time.mInNumber = (dataInDate.getUTCMonth() + 1);
        return time;
    }
};

let LocalStorage = {
    setArticleToLocalStorage: function (type, endpoint, endpointDetail) {
        let article = {
            type: type,
            endpoint: endpoint,
            endpointDetail, endpointDetail
        };
        localStorage.setItem('articleObject', JSON.stringify(article));
    },
    changeArticleLocalStorage: function (type, endpoint, endpointDetail) {
        if (localStorage.getItem('articleObject') !== null) {
            let article = JSON.parse(localStorage.getItem('articleObject'));
            article.type = type;
            article.endpoint = endpoint;
            article.endpointDetail = endpointDetail;
            localStorage.setItem('articleObject', JSON.stringify(article));
        }

    },
    getObject: function(object = ''){
        return JSON.parse(localStorage.getItem(object));
    }
}

let MoneyUtils = {
    viewPriceVND: function (price) {
        if (price) {
            return formatNumber(viewField(price), ",", ".") + "<u>đ</u>";
        }
        return "";
    }
};

let NumberUtils = {
    formatLargeNumber: function (nStr, decSeperate, groupSeperate) {
        nStr += '';
        let x = nStr.split(decSeperate);
        let x1 = x[0];
        let x2 = x.length > 1 ? '.' + x[1] : '';
        let rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + groupSeperate + '$2');
        }
        return x1 + x2;
    }
}

let StringHandle = {
    formatBlank: function (data) {
        return data.split('%20').join(' ');
    }
};

let FileUtil = {
    FILE_PREFIX: 'https://cdn.bksoftwarevn.com/resources/micro-upload/avhh/',
    getLinkFile: function (uri) {
        if (uri.startsWith('http')) {
            return uri;
        }
        return this.FILE_PREFIX + uri;
    },
    getFileName: function (data) {
        if (data !== null) {
            let listRawData = data.split('/');
            return listRawData[listRawData.length - 1];
        }
        return '';
    },
    getFileNameWithoutExtension: function (data) {
        let rawData = this.getFileName(data).split('.');
        return {nameFile: rawData[0], extension: rawData[1]};
    }
}