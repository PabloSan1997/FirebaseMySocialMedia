/// <reference types="vite/client" />

//-------User intarface

interface LoginDto{
    username:string;
    password:string;
}

interface RegisterDto{
    username:string;
    password:string;
    fullname:string;
}

interface UserInfo{
    username:string;
    fullname:string;
    description:string;
    urlImage:string;
    born:string;
    createCount:string;
}

interface UserHeader{
    username:string;
    fullname:string;
    urlImage:string;
}

interface ViewFollow{
    viewFollow:boolean
}

interface TokenDto{
    username:string;
    token:string;
}

interface SaveUserInfo{
    description:string;
    born:string
}

interface UserPictureResponse{
    urlImage:string;
}

interface ViewFollowCount{
    followings:number;
    followers:number;
}


//---------interaction interface

interface OneImagenInterface{
    id:number;
    urlImage:string;
    description:string;
    likes:number;
    comments:CommentInterface[],
    userLike:boolean;
    user:UserHeader;
    createAt:string;
}



interface ImagenInterface{
    id:number;
    urlImage:string;
    description:string;
    likes:number;
    comments:number;
    userLike:boolean;
    user:UserHeader;
    createAt:string;
}

interface CommentInterface {
    id:number;
    comment:string;
    createAt:string;
    user:UserHeader
}

interface SaveComment{
    comment:string;
}


interface LikeResponse{
    idImage:number;
    userLike:boolean
}

interface ErrorDto{
    statusCode:number;
    error:string;
    message:string;
    timestamp:string;
}

//-------------Initial State--------
interface UserInitialState{
    token:string;
    userHeader:UserHeader;
    userInfo:UserInfo;
    message:string;
    showProfileForm:boolean;
}

interface SocialInitialState{
    perfilUser:UserInfo;
    images:ImagenInterface[];
    oneImage:OneImagenInterface;
    message:string;
    userfollow:boolean;
    followsCount:ViewFollowCount;
    followHeaderUserInfo:UserHeader[]
}