//
//  BaseJsonService.m
//  EmbaClientForiPhone
//
//  Created by wuzhu on 12-6-28.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "BaseJsonService.h"
#import "StringUtil.h"
#import "NetWorkUtil.h"
#import "DataInterfaceUtil.h"
#import "ASIFormDataRequest.h"
#import "ResponseVO.h"
#import "UIDevice+IdentifierAddition.h"
#import "SVProgressHUD.h"
#import "FileUtil.h"
#import "UserVO.h"
#import "ApplicationSet.h"

@implementation BaseJsonService


#define CBOOL_DEBUG_JOSN YES
//是否读取本地assets
#define CBOOL_READASSETS YES

@synthesize tag2 = _tag2;

/**
 
 * @param delegate 处理获取数据结果的对象，需要包括BsseServiceDelegate ASIHTTPRequestDelegate中的处理方法
 * @param tag 标志位
 */
-(id)initWithDelegate:(id)delegate tag:(NSInteger)ktag
{
    self = [super init];
    if(self)
    {
        _delegate = delegate;
        _tag = ktag;
        _tag2 = 0;
    }
    return self;
}

-(void) getData:(NSString *) json
{
    [self getData:json url:nil process:NO processcontent:nil];
}

-(void) getData:(NSString *) json url:(NSString *)url
{
    [self getData:json url:url process:NO processcontent:nil];
}

-(void) getData:(NSString *) json url:(NSString *)url process:(BOOL)showprocess
{
    [self getData:json url:url process:showprocess processcontent:nil];
}

/**
 * 获取数据
 * @param json　请求报文
 * @param url　地址
 * @param showprogress 显示处理进度
 * @param progressshowcontent	显示处理进度提示内容，如果为null，显示默认提示
 */
-(void) getData:(NSString *)mjson url:(NSString *)murlstr process:(BOOL)mshowprocess processcontent:(NSString *)mshowprocesscontent
{
    if([StringUtil isEmptyStr:mjson])
    {
        [self sendServiceFailInfo:NSLocalizedString(@"未知服务请求（请求报文为空）", @"请求报文为空")];
        return;
    }
    if(CBOOL_DEBUG_JOSN)
        NSLog(@"BaseJsonService:%@",mjson);
    if (![NetWorkUtil checkNetWork:NO]) 
    {
        if (CBOOL_DEBUG_JOSN) {
            NSLog(@"BaseJsonService:%@",NSLocalizedString(@"Currently unable to access the server.", @"提示无法连接服务器"));
        }
        [self sendServiceFailInfo:NSLocalizedString(@"Currently unable to access the server.", @"提示无法连接服务器")];
        return;
    }
    
    //加载本地文件
    if(CBOOL_READASSETS)
    {
        NSString *suffix = @"";
        if(![StringUtil isEmptyStr:_suffix])
        {
            suffix = [[NSString alloc] initWithFormat:@"_%@",_suffix];
        }
        NSString *localjsoncontent = [FileUtil getAssetsFileContent:[[NSString alloc] initWithFormat:@"%@_response_data%@",_commandName,suffix] oftype:@"json"];
        if([StringUtil isEmptyStr:localjsoncontent])
        {
            [self sendServiceFailInfo:NSLocalizedString(@"Nothing info", @"提示无资讯信息")];
        }else
        {
            [self sendServiceSucessInfo:localjsoncontent];
        }
        return;
    }
    NSURL *url;
    if([StringUtil isEmptyStr:murlstr])
        url = [NSURL URLWithString:[DataInterfaceUtil getServerUrl]];
    else {
        url = [NSURL URLWithString:murlstr];
    }
    if(mshowprocess)
    {
        if ([StringUtil isEmptyStr:mshowprocesscontent]) {
            [SVProgressHUD showWithStatus:NSLocalizedString(@"Data Loading", @"提示数据加载中") maskType:SVProgressHUDMaskTypeClear];
        }else
        {
            [SVProgressHUD showWithStatus:mshowprocesscontent maskType:SVProgressHUDMaskTypeClear];
        }
    }
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    [request setTag:_tag];
    [request setPostValue:mjson forKey:@"requestjson"];
    [request setDelegate:self];
    [request setRequestMethod:@"POST"];
    [request setTimeOutSeconds:20];
    [request startAsynchronous];
}

-(void)requestFailed:(ASIHTTPRequest *)request
{
    [SVProgressHUD dismiss];
    [self sendServiceFailInfo:[[request error] localizedDescription]];
}

-(void)requestFinished:(ASIHTTPRequest *)request
{
    [SVProgressHUD dismiss];
    [self sendServiceSucessInfo:[request responseString]];
}


-(void)setAssetsFileInfo:(NSString *)kcommandName suffix:(NSString *)ksuffix
{
    _commandName = kcommandName;
    _suffix = ksuffix;
}

+(NSString *)getDevID
{
    return [[UIDevice currentDevice] uniqueDeviceIdentifier];
    //[[UIDevice currentDevice] uniqueGlobalDeviceIdentifier]
}
+(UserVO *)getUserInfo
{
    return [[ApplicationSet shareData] getUserVO];
}

/**
 *
 */
-(void) sendServiceFailInfo:(NSString*)msg
{
    if (CBOOL_DEBUG_JOSN)
    {
        NSLog(@"BaseJsonService sendServiceFailInfo:%@",msg);
    }
    if(!_delegate) return;
    if ([_delegate respondsToSelector:@selector(serviceResult:)])
    {
        ResponseVO *vo = [[ResponseVO alloc] initWithResult:CINT_CODE_ERROR msg:msg tag:_tag];
        vo.tag2 = self.tag2;
        [_delegate performSelector:@selector(serviceResult:) withObject:vo];
    }
}

-(void) sendServiceSucessInfo:(NSString*)conent
{
    if (CBOOL_DEBUG_JOSN)
    {
        NSLog(@"BaseJsonService sendServiceSucessInfo:%@",conent);
    }
    if(!_delegate) return;
    if ([_delegate respondsToSelector:@selector(serviceResult:)])
    {
        ResponseVO *vo = [[ResponseVO alloc] initWithResult:CINT_CODE_SUCESS msg:conent tag:_tag];
        vo.tag2 = self.tag2;
        [_delegate performSelector:@selector(serviceResult:) withObject:vo];
    }
}

@end
