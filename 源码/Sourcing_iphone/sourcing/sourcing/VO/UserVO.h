//
//  UserVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface UserVO : BaseVO
{
    NSInteger userid;
    NSString *usertype;
    NSString *useraccount;
    NSString *password;
    NSString *username;
    NSString *userphoto;
    NSString *phonenumber;
    NSString *email;
    NSString *consultcount;
    NSString *auditcount;
    NSString *declarecount;
    NSString *notifiercount;
    NSString *allconsultcount;
    NSString *allauditcount;
    NSString *alldeclarecount;
    NSString *allnotifiercount;
}
@property NSString *usertype;
@property NSString *useraccount;
@property NSString *password;
@property NSString *username;
@property NSString *userphoto;
@property NSString *phonenumber;
@property NSString *email;
@property NSString *consultcount;
@property NSString *auditcount;
@property NSString *declarecount;
@property NSString *notifiercount;
@property NSString *allconsultcount;
@property NSString *allauditcount;
@property NSString *alldeclarecount;
@property NSString *allnotifiercount;
-(void)setUserid:(NSInteger )kuserid;
-(NSInteger )Userid;

+(BOOL)saveLoginUserInfo:(UserVO *)kuservo;
+(UserVO *)getLastLoginUserInfo;

@end
