//
//  UserVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "UserVO.h"

@implementation UserVO

@synthesize usertype = usertype;
@synthesize useraccount = useraccount;
@synthesize password = password;
@synthesize username = username;
@synthesize userphoto = userphoto;
@synthesize phonenumber = phonenumber;
@synthesize email = email;
@synthesize consultcount = consultcount;
@synthesize auditcount = auditcount;
@synthesize declarecount = declarecount;
@synthesize notifiercount = notifiercount;
@synthesize allconsultcount = allconsultcount;
@synthesize allauditcount = allauditcount;
@synthesize alldeclarecount = alldeclarecount;
@synthesize allnotifiercount = allnotifiercount;

-(void)setUserid:(NSString *)kuserid
{
    _id = kuserid;
    userid = kuserid;
}
-(NSString *)Userid
{
    return userid;
}

@end
