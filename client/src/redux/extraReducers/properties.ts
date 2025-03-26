

const mainhost = import.meta.env.DEV?'localhost:3005':window.location.host;


export const apiBase = `http://${mainhost}/api`;

export const size = 10;