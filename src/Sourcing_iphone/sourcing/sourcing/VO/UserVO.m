//
//  UserVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "UserVO.h"
#import "FileUtil.h"
#import "AutoCoding.h"
#import "FavoriteUtil.h"

@interface UserVO ()
{
@private
    NSInteger userid;
}
@end

@implementation UserVO

@synthesize usertype = usertype_;
@synthesize useraccount = useraccount_;
@synthesize password = password_;
@synthesize username = username_;
@synthesize userphoto = userphoto_;
@synthesize phonenumber = phonenumber_;
@synthesize email = email_;
@synthesize consultcount = consultcount_;
@synthesize auditcount = auditcount_;
@synthesize declarecount = declarecount_;
@synthesize notifiercount = notifiercount_;
@synthesize allconsultcount = allconsultcount_;
@synthesize allauditcount = allauditcount_;
@synthesize alldeclarecount = alldeclarecount_;
@synthesize allnotifiercount = allnotifiercount_;

-(void)setUserid:(NSInteger )kuserid
{
    self.Id = kuserid;
    userid = kuserid;
}
-(NSInteger )Userid
{
    return userid;
}

+(NSString *)getUserInfoFilePath
{
    return [FileUtil createFilePathForLocation:FileDirectoryDocuments withFileName:@"datafile_u" withExtension:@"dat"];
}

+(BOOL)saveLoginUserInfo:(UserVO *)kuservo
{
    NSString *filepath = [UserVO getUserInfoFilePath];
    BOOL result = NO;
     if([[NSFileManager defaultManager] fileExistsAtPath:filepath])
     {
        result = [[NSFileManager defaultManager] removeItemAtPath:filepath error:nil];
     }
    if(kuservo != nil)
    {
        return [NSKeyedArchiver archiveRootObject:kuservo toFile:filepath];
    }
    return result;
}

+(UserVO *)getLastLoginUserInfo
{
    NSString *filepath = [UserVO getUserInfoFilePath];
    if(![[NSFileManager defaultManager] fileExistsAtPath:filepath])
        return nil;
    UserVO * result = [NSKeyedUnarchiver unarchiveObjectWithFile:filepath];
    return result;
}

-(NSString *)getStrUserType
{
    if(self.usertype == USER_TYPE_PERSONAL)
        return @"个人用户";
    else if(self.usertype == USER_TYPE_EXPERT)
        return @"专家用户";
    else if(self.usertype == USER_TYPE_ENTERPRISE)
        return @"企业用户";
    else if(self.usertype == USER_TYPE_TRAIN)
        return @"培训机构";
    return @"";
}
-(BOOL)hasConsult
{
    return self.usertype != USER_TYPE_NORMAL;
}
-(BOOL)hasAudit
{
    return self.usertype == USER_TYPE_EXPERT||(self.usertype == USER_TYPE_ENTERPRISE)||(self.usertype == USER_TYPE_TRAIN);
}
-(BOOL)hasDeclare
{
    return self.usertype == USER_TYPE_ENTERPRISE || self.usertype == USER_TYPE_TRAIN;
}
-(BOOL)hasNotifier
{
    return self.usertype != USER_TYPE_NORMAL;
}

-(NSInteger)hasInfoCount
{
    NSInteger count = 0;
    if ([self hasConsult]) 
        count++;
    if ([self hasAudit])
        count++;
    if ([self hasDeclare])
        count++;
    if ([self hasNotifier])
        count++;
    return count;
}

-(NSArray *)infoTypes
{
    NSMutableArray *result = [NSMutableArray arrayWithCapacity:CINT_USERINFOTYPE_MAXCOUNT];
    [result addObject:[NSNumber numberWithInt:1]];
    [result addObject:[NSNumber numberWithInt:[self hasConsult]?1:0]];
    [result addObject:[NSNumber numberWithInt:[self hasAudit]?1:0]];
    [result addObject:[NSNumber numberWithInt:[self hasDeclare]?1:0]];
    [result addObject:[NSNumber numberWithInt:[self hasNotifier]?1:0]];
    return result;
}

-(USER_INFO_TYPE)getInfoType:(NSInteger)kindex
{
    if(kindex<=0||kindex>=CINT_USERINFOTYPE_MAXCOUNT)
        return USER_INFO_TYPE_FAV;
    
    NSArray *array = [self infoTypes];
    NSInteger result = USER_INFO_TYPE_FAV;
    for(NSInteger i = 0;i<kindex&&i<CINT_USERINFOTYPE_MAXCOUNT&&result<CINT_USERINFOTYPE_MAXCOUNT;i++)
    {
        NSNumber *number = [array objectAtIndex:i];
        if([number integerValue]==1)
            result++;
        else
            result += 2;
    }
    return result;
}

-(NSString *)getInfoMsg:(NSInteger)kindex
{
    USER_INFO_TYPE type = [self getInfoType:kindex];
    
    if(type == USER_INFO_TYPE_CONSULT)
        return [NSString stringWithFormat:@"咨询信息（未查看:%d，全部:%d）",self.consultcount,self.allconsultcount];
    else if(type == USER_INFO_TYPE_AUDIT)
        return [NSString stringWithFormat:@"审核结果（未查看:%d，全部:%d）",self.auditcount,self.allauditcount];
    else if(type == USER_INFO_TYPE_DECLARE)
        return [NSString stringWithFormat:@"申报进度（未查看:%d，全部:%d）",self.declarecount,self.alldeclarecount];
    else if(type == USER_INFO_TYPE_NOTIFIER)
        return [NSString stringWithFormat:@"通知提醒（未查看:%d，全部:%d）",self.notifiercount,self.allnotifiercount];
    
    return [UserVO getInfoMsgWithFav];
    
}

+(NSString *)getUserInfoDesc:(USER_INFO_TYPE)kinfotype
{
    switch (kinfotype) {
        case USER_INFO_TYPE_CONSULT:
            return @"咨询信息";
        case USER_INFO_TYPE_AUDIT:
            return @"审核结果";
        case USER_INFO_TYPE_DECLARE:
            return @"申报进度";
        case USER_INFO_TYPE_NOTIFIER:
            return @"通知提醒";
        default:
            return @"我的收藏";
    }
}

+(NSString *)getInfoMsgWithFav
{
    return [NSString stringWithFormat:@"我的收藏（收藏数:%d）",[[FavoriteUtil favoriteUtil] getFavNumber]];
}
@end
