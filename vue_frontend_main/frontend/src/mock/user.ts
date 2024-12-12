export default [

    {
        url: '/mock/user/permission',
        method: 'get',
        response: ({ headers }: any) => {
            const token = headers.token;
            const tokenParts = token.split('_');
            const roleid = tokenParts[2];

            let permissions: string[] = [];

            if (roleid === '3') {
                permissions = [
                    'permission.admin',
                ]
            }
            else if (roleid === '0') {
                permissions = [
                    'permission.student',
                ]
            }
            else if(roleid === '1'){
                permissions = [
                    'permission.school',
                ]
            }
            else if(roleid === '2'){
                permissions = [
                    'permission.company',
                ]
            }
            return {
                error: '',
                status: 1,
                data: {
                    permissions,
                },
            }
        },
    },
    {
        url: '/mock/user/info',
        method: 'get',
        response: ({ headers }: any) => {
            const token = headers.token;
            const tokenParts = token.split('_');
            let userid = [tokenParts[0]];
            let majorid = [tokenParts[1]];
            let roleid = [tokenParts[2]];
            let cid = [tokenParts[3]];
            return {
                data: {
                    userid,
                    majorid,
                    roleid,
                    cid
                },
            }
        },
    },
    {
        url: '/mock/user/password/edit',
        method: 'post',
        response: {
            error: '',
            status: 1,
            data: {
                isSuccess: true,
            },
        },
    },
]
