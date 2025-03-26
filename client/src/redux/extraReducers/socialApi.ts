import { ActionReducerMapBuilder, createAsyncThunk } from "@reduxjs/toolkit";
import { apiBase, size } from "./properties";
import { routesName } from "../../utils/routesName";



export const socialApi = {
    findAll: createAsyncThunk(
        'extraReducer/findall',
        async ({ token, page }: { token: string, page: number }): Promise<ImagenInterface[]> => {
            try {
                const ft = await fetch(`${apiBase}/image?page=${page}&size=${size}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const err = error as ErrorDto;
                throw { message: err.message };
            }
        }
    ),
    saveNewImage: createAsyncThunk(
        'extraReducer/saveimage',
        async ({ token, formdata }: { token: string, formdata: FormData }) => {
            try {
                const ft = await fetch(`${apiBase}/image`, {
                    method: 'POST',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                    body: formdata
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const err = error as ErrorDto;
                throw { message: err.message };
            }
        }
    ),
    findOneImage: createAsyncThunk(
        'extraReducer/findOneImage',
        async ({ token, idImage, page }: { token: string, page: number, idImage: number }): Promise<OneImagenInterface> => {
            try {
                const ft = await fetch(`${apiBase}/image/${idImage}?page=${page}&size=${size}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                if (!ft.ok)
                    throw await ft.json();
                return ft.json();
            } catch (error) {
                const err = error as ErrorDto;
                throw { message: err.message };
            }
        }
    ),
    addComment: createAsyncThunk(
        'extraReducer/addcomment',
        async ({ token, idImage, comment }: { token: string, idImage: number, comment: string }): Promise<CommentInterface> => {
            const data: SaveComment = {
                comment
            }
            const ft = await fetch(`${apiBase}/interaction/comment/${idImage}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    findFriendImages: createAsyncThunk(
        'extraReducer/findFriendImages',
        async ({ token, friendname, page }: { token: string, friendname: string, page: number }): Promise<ImagenInterface[]> => {
            const ft = await fetch(`${apiBase}/image/user/${friendname}?size=${size}&page=${page}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    findFollowinsImage:createAsyncThunk(
        'extraReducer/findFollowingsUsers',
        async ({ token, page }: { token: string, page: number }): Promise<ImagenInterface[]> => {
            const ft = await fetch(`${apiBase}/image/following?size=${size}&page=${page}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`,
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    findFriendInfo: createAsyncThunk(
        'extraReducer/findFriendInformation',
        async ({ token, friendname }: { token: string, friendname: string }): Promise<UserInfo> => {
            const ft = await fetch(`${apiBase}/friend/infouser/${friendname}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    generateLike: createAsyncThunk(
        'extraReducer/generateLike',
        async ({ token, idImage }: { token: string, idImage: number }): Promise<LikeResponse> => {
            const ft = await fetch(`${apiBase}/interaction/like/${idImage}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    generateOneImageLike: createAsyncThunk(
        'extraReducer/generateLikeOneImage',
        async ({ token, idImage }: { token: string, idImage: number }): Promise<LikeResponse> => {
            const ft = await fetch(`${apiBase}/interaction/like/${idImage}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    viewFollowFriend: createAsyncThunk(
        'extraReducer/viewFollowFriend',
        async ({token, friendname}:{token:string, friendname:string}):Promise<ViewFollow>=>{
            const ft = await fetch(`${apiBase}/friend/viewfollow/${friendname}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    viewCountFollow: createAsyncThunk(
        'extraReducer/viewCountFollow',
        async ({token, friendname}:{token:string, friendname:string}): Promise<ViewFollowCount> =>{
            const ft = await fetch(`${apiBase}/friend/followsfriend/${friendname}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    findFollowsUserHeader: createAsyncThunk(
        'extraReducer/findFollowers',
        async ({token, friendname, followers}:{token:string, friendname:string, followers:boolean}): Promise<UserHeader[]> =>{
            const options:'followers'|'followings' = followers?'followers':'followings';
            const ft = await fetch(`${apiBase}/friend/${options}/${friendname}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    createFollow: createAsyncThunk(
        'extraReducer/createFollow',
        async ({friendname, token}:{friendname:string, token:string}):Promise<ViewFollowCount> =>{
            const ft = await fetch(`${apiBase}/friend/${friendname}`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return ft.json();
        }
    ),
    deleteImageById:createAsyncThunk(
        'extraReducer/deleteImage',
        async ({token, id}:{token:string, id:number}):Promise<{id:number}>=>{
            const ft = await fetch(`${apiBase}/image/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return {id}
        }
    ),
    deleteCommentById:createAsyncThunk(
        'extraReducer/deleteComment',
        async ({token, id}:{token:string, id:number}):Promise<{id:number}>=>{
            const ft = await fetch(`${apiBase}/interaction/comment/${id}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
            if (!ft.ok)
                throw await ft.json();
            return {id}
        }
    )
}



export function generateSocialExtraReducer(builder: ActionReducerMapBuilder<SocialInitialState>) {
    builder.addCase(socialApi.findAll.fulfilled, (state, action) => {
        state.images = action.payload;
        state.message = '';
    });
    builder.addCase(socialApi.findAll.rejected, (state) => {
        state.images = [];
    });

    builder.addCase(socialApi.saveNewImage.fulfilled, (state, action) => {
        state.images = [action.payload, ...state.images];
    });
    builder.addCase(socialApi.saveNewImage.rejected, (state, action) => {
        const message = action.error.message;
        state.message = message ? message : 'Error al enviar publicacion';
    });

    builder.addCase(socialApi.findOneImage.fulfilled, (state, action) => {
        state.oneImage = action.payload;
    });

    builder.addCase(socialApi.findOneImage.rejected, () => {
        window.location.href = `/#${routesName.home}`;
    });

    builder.addCase(socialApi.addComment.fulfilled, (state, action) => {
        state.oneImage.comments = [action.payload, ...state.oneImage.comments];
    });

    builder.addCase(socialApi.findFriendImages.fulfilled, (state, action) => {
        state.images = action.payload;
    });

    builder.addCase(socialApi.findFollowinsImage.fulfilled, (state, action) => {
        state.images = action.payload;
    });

    builder.addCase(socialApi.findFriendInfo.fulfilled, (state, action) => {
        state.perfilUser = action.payload;
    });

    builder.addCase(socialApi.generateLike.fulfilled, (state, action) => {
        const index = state.images.findIndex(p => p.id == action.payload.idImage);
        state.images[index].userLike = action.payload.userLike;
        const coutnlikes = state.images[index].likes;
        state.images[index].likes = action.payload.userLike ? coutnlikes + 1 : coutnlikes - 1;
    });
    builder.addCase(socialApi.generateOneImageLike.fulfilled, (state, action)=>{
        state.oneImage.userLike = action.payload.userLike;
        const countLikes = state.oneImage.likes;
        state.oneImage.likes = action.payload.userLike?countLikes+1:countLikes-1;
    });

    builder.addCase(socialApi.viewFollowFriend.fulfilled, (state, action)=>{
        state.userfollow = action.payload.viewFollow;
    });

    builder.addCase(socialApi.viewCountFollow.fulfilled, (state, action)=>{
        state.followsCount = action.payload;
    });

    builder.addCase(socialApi.findFollowsUserHeader.fulfilled, (state, action)=>{
        state.followHeaderUserInfo = action.payload;
    });

    builder.addCase(socialApi.createFollow.fulfilled, (state, action)=>{
        state.followsCount = action.payload;
    });

    builder.addCase(socialApi.deleteImageById.fulfilled, (state, action)=>{
        const index = state.images.findIndex(p => p.id == action.payload.id);
        const clone = [...state.images];
        clone.splice(index, 1);
        state.images = clone;
        state.oneImage = {...state.oneImage, id:0}
    });

    builder.addCase(socialApi.deleteCommentById.fulfilled, (state, action)=>{
        const comments = [...state.oneImage.comments];
        const index = comments.findIndex(p => p.id == action.payload.id);
        comments.splice(index, 1);
        state.oneImage.comments = comments;
    });
}