//
//  UserVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

#define CINT_USERINFOTYPE_MAXCOUNT 5

typedef enum USER_TYPE:NSInteger
{
    USER_TYPE_NORMAL = 0,
    USER_TYPE_PERSONAL = 1,
    USER_TYPE_EXPERT = 2,
    USER_TYPE_ENTERPRISE = 3,
    USER_TYPE_TRAIN = 4
}USER_TYPE;

typedef enum USER_INFO_TYPE:NSInteger
{
    USER_INFO_TYPE_FAV =0,
    USER_INFO_TYPE_CONSULT =1,
    USER_INFO_TYPE_AUDIT =2,
    USER_INFO_TYPE_DECLARE =3,
    USER_INFO_TYPE_NOTIFIER =4
}USER_INFO_TYPE;

@interface UserVO : BaseVO

@property (nonatomic)NSInteger usertype;
@property (nonatomic,strong)NSString *useraccount;
@property (nonatomic,strong)NSString *password;
@property (nonatomic,strong)NSString *username;
@property (nonatomic,strong)NSString *userphoto;
@property (nonatomic,strong)NSString *phonenumber;
@property (nonatomic,strong)NSString *email;
@property (nonatomic)NSInteger consultcount;
@property (nonatomic)NSInteger auditcount;
@property (nonatomic)NSInteger declarecount;
@property (nonatomic)NSInteger notifiercount;
@property (nonatomic)NSInteger allconsultcount;
@property (nonatomic)NSInteger allauditcount;
@property (nonatomic)NSInteger alldeclarecount;
@property (nonatomic)NSInteger allnotifiercount;


-(void)setUserid:(NSInteger )kuserid;
-(NSInteger )Userid;

-(NSString *)getStrUserType;
-(BOOL)hasConsult;
-(BOOL)hasAudit;
-(BOOL)hasDeclare;
-(BOOL)hasNotifier;
-(NSInteger)hasInfoCount;
-(USER_INFO_TYPE)getInfoType:(NSInteger)kindex;
-(NSString *)getInfoMsg:(NSInteger)kindex;

+(BOOL)saveLoginUserInfo:(UserVO *)kuservo;
+(UserVO *)getLastLoginUserInfo;
+(NSString *)getInfoMsgWithFav;
+(NSString *)getUserInfoDesc:(USER_INFO_TYPE)kinfotype;

@end
