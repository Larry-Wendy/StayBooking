/* 前后端通信 
 * API: fetch()
 * 为了解决跨域通信，第一个项目用了proxy，这个项目用了response header
 * 第一个项目一起deploy，这个项目前后端可以分开deploy
 * 
*/

//const domain = "http://localhost:8080";
//const domain = "https://staybooking-362323.ue.r.appspot.com";
const domain = "";
// credentials = { username : string, password : string }
// asHost : boolean
// JSDOC
/**
 * 
 * @param {*} credential 
 * @param {*} asHost 
 */
export const login = (credential, asHost) => {
    const loginUrl = `${domain}/authenticate/${asHost ? "host" : "guest"}`; // template string from EX6
    return fetch(loginUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(credential),
    }).then((response) => { // 请求成功后执行
        if (response.status !== 200) {
            throw Error("Fail to log in");
        }

        return response.json(); // 后端处理回来之后跑
    });
};

export const register = (credential, asHost) => {
    const registerUrl = `${domain}/register/${asHost ? "host" : "guest"}`;
    return fetch(registerUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(credential),
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to register");
      }
    });
};

export const getReservations = () => { // guest调查自己订了啥
    const authToken = localStorage.getItem("authToken");
    const listReservationsUrl = `${domain}/reservations`;
   
    return fetch(listReservationsUrl, { // get request -- default 不用配置
      headers: { // 固定写法
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to get reservation list");
      }
   
      return response.json();
    });
};

export const getStaysByHost = () => {
    const authToken = localStorage.getItem("authToken");
    const listStaysUrl = `${domain}/stays/`;
   
    return fetch(listStaysUrl, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to get stay list");
      }
   
      return response.json();
    });
};

export const searchStays = (query) => {
    const authToken = localStorage.getItem("authToken");
    const searchStaysUrl = new URL(`http://localhost:3000/search/`);
    searchStaysUrl.searchParams.append("guest_number", query.guest_number);
    searchStaysUrl.searchParams.append(
      "checkin_date",
      query.checkin_date.format("YYYY-MM-DD")
    );
    searchStaysUrl.searchParams.append(
      "checkout_date",
      query.checkout_date.format("YYYY-MM-DD")
    );
    searchStaysUrl.searchParams.append("lat", 37);
    searchStaysUrl.searchParams.append("lon", -122);
   
    return fetch(searchStaysUrl, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to search stays");
      }
   
      return response.json();
    });
};

export const deleteStay = (stayId) => {
    const authToken = localStorage.getItem("authToken");
    const deleteStayUrl = `${domain}/stays/${stayId}`;
   
    return fetch(deleteStayUrl, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to delete stay");
      }
    });
};
   
  export const bookStay = (data) => {
    const authToken = localStorage.getItem("authToken");
    const bookStayUrl = `${domain}/reservations`;
   
    return fetch(bookStayUrl, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${authToken}`,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to book reservation");
      }
    });
  };
   
  export const cancelReservation = (reservationId) => {
    const authToken = localStorage.getItem("authToken");
    const cancelReservationUrl = `${domain}/reservations/${reservationId}`;
   
    return fetch(cancelReservationUrl, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to cancel reservation");
      }
    });
  };
   
  export const getReservationsByStay = (stayId) => {
    const authToken = localStorage.getItem("authToken");
    const getReservationByStayUrl = `${domain}/stays/reservations/${stayId}`;
   
    return fetch(getReservationByStayUrl, {
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to get reservations by stay");
      }
   
      return response.json();
    });
  };
   
  export const uploadStay = (data) => {
    const authToken = localStorage.getItem("authToken");
    const uploadStayUrl = `${domain}/stays`;
   
    return fetch(uploadStayUrl, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${authToken}`,
      },
      body: data,
    }).then((response) => {
      if (response.status !== 200) {
        throw Error("Fail to upload stay");
      }
    });
  };
  
  
  
  