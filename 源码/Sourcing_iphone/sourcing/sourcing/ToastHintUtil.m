//
//  ToastHintUtil.m
//  sourcing
//
//  Created by wuzhu on 12-12-28.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ToastHintUtil.h"
#import "iToast.h"
#import "MBProgressHUD.h"

@implementation ToastHintUtil
+(void)showHint:(NSString *)msg parentview:(UIView *)view
{
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:view animated:YES];
    hud.mode = MBProgressHUDModeText;
    hud.labelText = msg;
    hud.yOffset = 150.0f;
    hud.removeFromSuperViewOnHide = YES;
    
    [hud hide:YES afterDelay:2];
}

+(void)showHint:(NSString *)msg
{
    [[[[iToast makeText:msg] setGravity:iToastGravityBottom] setDuration:iToastDurationNormal] show];
}
@end
