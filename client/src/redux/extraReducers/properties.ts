

const mainhost = import.meta.env.DEV?'http://localhost:3005':window.location.origin;


export const apiBase = `${mainhost}/api`;

export const size = 10;